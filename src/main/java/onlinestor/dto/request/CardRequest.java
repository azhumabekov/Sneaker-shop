package onlinestor.dto.request;

import lombok.Data;

@Data
public class CardRequest {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
