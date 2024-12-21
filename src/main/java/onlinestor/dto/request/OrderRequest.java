package onlinestor.dto.request;

import lombok.Data;

@Data
public class OrderRequest {
    private Long userId;
    private String address;
}
