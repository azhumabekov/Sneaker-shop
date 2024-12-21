package onlinestor.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String imageUrl;
    private Double price;
    private Integer stock;
    private String category;
}
