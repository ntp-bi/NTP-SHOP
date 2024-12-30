package com.ntp.ntpshops.service.cart;

import com.ntp.ntpshops.model.Cart;
import com.ntp.ntpshops.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCartById(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
