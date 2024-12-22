package onlinestor.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import onlinestor.dto.request.CartRequest;
import onlinestor.dto.response.CartResponse;
import onlinestor.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Tag(name = "Cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartResponse> getCart(@RequestParam Long userId) {
        return cartService.getCart(userId);
    }

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody CartRequest cartRequest) {
        cartService.addToCart(cartRequest);
        return ResponseEntity.ok("Product added to cart");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeFromCart(
            @PathVariable Long productId,
            @RequestParam Long userId
    ) {
        cartService.removeFromCart(userId, productId);
        return ResponseEntity.ok("Product removed from cart");
    }
}