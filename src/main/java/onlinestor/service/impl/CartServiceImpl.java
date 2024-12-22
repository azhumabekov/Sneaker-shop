package onlinestor.service.impl;

import lombok.RequiredArgsConstructor;
import onlinestor.dto.request.CartRequest;
import onlinestor.dto.response.CartResponse;
import onlinestor.models.Cart;
import onlinestor.models.Product;
import onlinestor.repository.CartRepository;
import onlinestor.repository.ProductRepository;
import onlinestor.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public List<CartResponse> getCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addToCart(CartRequest cartRequestDTO) {
        Optional<Product> productOpt = productRepository.findById(cartRequestDTO.getProductId());
        if (productOpt.isEmpty() || productOpt.get().getStock() < cartRequestDTO.getQuantity()) {
            throw new RuntimeException("Product out of stock or does not exist");
        }
        Cart cart = new Cart();
        cart.setUserId(cartRequestDTO.getUserId());
        cart.setProductId(cartRequestDTO.getProductId());
        cart.setQuantity(cartRequestDTO.getQuantity());
        cartRepository.save(cart);
    }

    @Override
    public void removeFromCart(Long userId, Long productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    private CartResponse convertToDTO(Cart cart) {
        Optional<Product> productOpt = productRepository.findById(cart.getProductId());
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Product not found in database");
        }
        Product product = productOpt.get();
        CartResponse dto = new CartResponse();
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setProductPrice(product.getPrice());
        dto.setQuantity(cart.getQuantity());
        dto.setTotalPrice(cart.getQuantity() * product.getPrice());
        return dto;
    }
}