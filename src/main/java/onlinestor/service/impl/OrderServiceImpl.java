package onlinestor.service.impl;

import lombok.RequiredArgsConstructor;
import onlinestor.dto.request.OrderRequest;
import onlinestor.dto.response.OrderResponse;
import onlinestor.models.Cart;
import onlinestor.models.Order;
import onlinestor.models.Product;
import onlinestor.repository.CartRepository;
import onlinestor.repository.OrderRepository;
import onlinestor.repository.ProductRepository;
import onlinestor.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        List<Cart> cartItems = cartRepository.findByUserId(orderRequest.getUserId());
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double totalPrice = 0;

        for (Cart cart : cartItems) {
            Optional<Product> product = productRepository.findById(cart.getProductId());
            if (product.isPresent()) {
                totalPrice += cart.getQuantity() * product.get().getPrice();
            } else {
                throw new RuntimeException("Product not found in the database");
            }
        }

        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setAddress(orderRequest.getAddress());
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        cartRepository.deleteAll(cartItems);

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setAddress(order.getAddress());
        response.setTotalPrice(order.getTotalPrice());

        return response;
    }
}
