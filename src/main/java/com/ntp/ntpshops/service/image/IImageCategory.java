package com.ntp.ntpshops.service.image;

import com.ntp.ntpshops.dto.ImageDTO;
import com.ntp.ntpshops.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageCategory {
    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDTO> saveImages(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long imageId);
}
