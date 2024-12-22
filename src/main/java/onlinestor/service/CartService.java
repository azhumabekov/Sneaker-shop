package onlinestor.service;

import onlinestor.dto.request.CartRequest;
import onlinestor.dto.response.CartResponse;

import java.util.List;

public interface CartService {
    List<CartResponse> getCart(Long userId);

    void addToCart(CartRequest cartRequest);

    void removeFromCart(Long userId, Long productId);
}