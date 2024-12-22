package onlinestor.service;

import onlinestor.dto.request.OrderRequest;
import onlinestor.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequestDTO);

}
