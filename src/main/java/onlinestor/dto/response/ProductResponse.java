package onlinestor.dto.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private Double price;
    private Integer stock;
    private String category;
}