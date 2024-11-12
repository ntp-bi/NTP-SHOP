package com.ntp.ntpshops.service.image;

import com.ntp.ntpshops.dto.ImageDTO;
import com.ntp.ntpshops.exception.ResourceNotFoundException;
import com.ntp.ntpshops.model.Image;
import com.ntp.ntpshops.model.Product;
import com.ntp.ntpshops.repository.ImageRepository;
import com.ntp.ntpshops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageCategory {
    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not image found with id: " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("Not image found with id: " + id);
        });
    }

    @Override
    public List<ImageDTO> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);

        List<ImageDTO> savedImageDTO = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "/api/v1/images/image/download/";

                String downloadUrl = buildDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);

                savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
                imageRepository.save(savedImage);

                ImageDTO imageDTO = new ImageDTO();

                imageDTO.setImageId(savedImage.getId());
                imageDTO.setImageName(savedImage.getFileName());
                imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDTO.add(imageDTO);

            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDTO;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
