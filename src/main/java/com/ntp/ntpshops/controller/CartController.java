package com.ntp.ntpshops.controller;

import com.ntp.ntpshops.exception.ResourceNotFoundException;
import com.ntp.ntpshops.model.Cart;
import com.ntp.ntpshops.response.ApiResponse;
import com.ntp.ntpshops.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("${api.prefix}/carts")
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;

    @GetMapping("/{cartId}/my-cart")
    public ResponseEntity<ApiResponse> getCartById(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCartById(cartId);
            return ResponseEntity.ok(new ApiResponse("Success", cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Clear cart success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<ApiResponse> getTotalAmount(@PathVariable Long cartId) {
        try {
            BigDecimal totalPrice = cartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Success", totalPrice));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
