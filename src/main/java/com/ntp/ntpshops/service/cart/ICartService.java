package com.ntp.ntpshops.service.cart;

import com.ntp.ntpshops.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCartById(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();
}
