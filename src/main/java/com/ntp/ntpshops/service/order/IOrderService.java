package com.ntp.ntpshops.service.order;

import com.ntp.ntpshops.model.Order;

public interface IOrderService {
    Order placeOrder(Long userId);

    Order getOrder(Long orderId);
}
