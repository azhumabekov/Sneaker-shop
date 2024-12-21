package onlinestor.dto.response;

import lombok.Data;

@Data
public class OrderResponse {
    private Long orderId;
    private String address;
    private Double totalPrice;
}
