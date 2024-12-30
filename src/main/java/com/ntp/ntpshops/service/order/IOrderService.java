package com.ntp.ntpshops.service.order;

import com.ntp.ntpshops.dto.OrderDTO;
import com.ntp.ntpshops.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);

    OrderDTO getOrder(Long orderId);

    List<OrderDTO> getUserOrders(Long userId);

    OrderDTO convertToDto(Order order);
}
