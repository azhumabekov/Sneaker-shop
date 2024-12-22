package onlinestor.service;

import onlinestor.dto.request.ProductRequest;
import onlinestor.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts(String category, Double maxPrice);

    ProductResponse createProduct(ProductRequest productRequest);
}
