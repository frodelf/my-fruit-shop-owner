package test.myfruitshopowner.mapper;

import test.myfruitshopowner.dto.product.ProductResponseDto;
import test.myfruitshopowner.entity.Owner;
import test.myfruitshopowner.entity.Product;

import java.util.Collections;

public class ProductMapper {
    public static ProductResponseDto toDto(Product product, Owner owner, String lastOperation) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setOwner(Collections.singletonMap(owner.getId(), owner.getName()));
        productResponseDto.setLastOperation(lastOperation);
        return productResponseDto;
    }
}