package com.ntp.ntpshops.service.order;

import com.ntp.ntpshops.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);

    Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}
