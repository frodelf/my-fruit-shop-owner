package test.myfruitshopowner.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.dto.FilterDto;
import test.myfruitshopowner.dto.product.ProductResponseDto;

public interface ProductService {
    Flux<ProductResponseDto> getAll(FilterDto filterDto);
    Mono<Long> getTotalElementForPagination(int pageSize, String name);
    Mono<Long> getCountByNameLike(String name);
}