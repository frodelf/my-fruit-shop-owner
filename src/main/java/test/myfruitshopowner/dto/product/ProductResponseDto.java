package test.myfruitshopowner.dto.product;

import lombok.Data;

import java.util.Map;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private Long number;
    private Map<Long, String> owner;
    private String lastOperation;
}