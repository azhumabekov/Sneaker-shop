package onlinestor.service.impl;

import lombok.RequiredArgsConstructor;
import onlinestor.dto.request.ProductRequest;
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

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        if (productRequest.getCategory() == null) {
            productRequest.setCategory("");
        }
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setImageUrl(productRequest.getImageUrl());
        product.setCategory(productRequest.getCategory());

        Product savedProduct = productRepository.save(product);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(savedProduct.getId());
        productResponse.setName(savedProduct.getName());
        productResponse.setPrice(savedProduct.getPrice());
        productResponse.setStock(savedProduct.getStock());
        productResponse.setImageUrl(savedProduct.getImageUrl());
        productResponse.setCategory(savedProduct.getCategory());

        return productResponse;
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
