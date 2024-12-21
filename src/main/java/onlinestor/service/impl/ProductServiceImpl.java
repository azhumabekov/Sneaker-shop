package onlinestor.service.impl;

import lombok.RequiredArgsConstructor;
import onlinestor.dto.response.ProductResponse;
import onlinestor.models.Product;
import onlinestor.repository.ProductRepository;
import onlinestor.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProducts(String category, Double maxPrice) {
        return productRepository.findAll().stream()
                .filter(p -> (category == null || p.getCategory().equalsIgnoreCase(category)) &&
                        (maxPrice == null || p.getPrice() <= maxPrice))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private ProductResponse convertToDTO(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setImageUrl(product.getImageUrl());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategory(product.getCategory());
        return dto;
    }
}
