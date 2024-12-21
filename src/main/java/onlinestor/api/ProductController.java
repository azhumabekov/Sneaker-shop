package onlinestor.api;

import lombok.RequiredArgsConstructor;
import onlinestor.dto.response.ProductResponse;
import onlinestor.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double maxPrice
    ) {
        return productService.getAllProducts(category, maxPrice);
    }

}