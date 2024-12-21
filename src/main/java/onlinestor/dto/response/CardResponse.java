package onlinestor.dto.response;

import lombok.Data;

@Data
public class CardResponse {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Double totalPrice;
}