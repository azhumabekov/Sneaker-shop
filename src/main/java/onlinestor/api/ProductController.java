package onlinestor.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import onlinestor.dto.request.ProductRequest;
import onlinestor.dto.response.ProductResponse;
import onlinestor.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts(@RequestParam(required = false) String category, @RequestParam(required = false) Double maxPrice) {
        return productService.getAllProducts(category, maxPrice);
    }

    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }
}