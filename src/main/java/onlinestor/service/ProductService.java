package onlinestor.service;

import onlinestor.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts(String category, Double maxPrice);

}
