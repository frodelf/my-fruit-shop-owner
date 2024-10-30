package test.myfruitshopowner.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.dto.FilterDto;
import test.myfruitshopowner.dto.product.ProductResponseDto;
import test.myfruitshopowner.mapper.ProductMapper;
import test.myfruitshopowner.repository.ProductRepository;
import test.myfruitshopowner.service.HistoryService;
import test.myfruitshopowner.service.OwnerService;
import test.myfruitshopowner.service.ProductService;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final OwnerService ownerService;
    private final HistoryService historyService;
    private final ProductRepository productRepository;
    @Override
    //TODO Fix it!!
    public Flux<ProductResponseDto> getAll(FilterDto filterDto) {
        return ownerService.getAuthenticatedUserId()
                .flatMapMany(userId -> {
                    Pageable pageable = PageRequest.of(filterDto.getPage(), filterDto.getPageSize(), Sort.by(Sort.Order.desc("id")));
                    return productRepository.findAllByUserIdAndNameContaining(userId, filterDto.getQuery(), pageable)
                            .flatMap(product ->
                                    ownerService.getById(product.getUserId())
                                            .flatMap(owner ->
                                                    historyService.getLastByProductId(product.getId())
                                                            .map(history -> ProductMapper.toDto(product, owner, history))
                                            )
                            );
                });
    }


    @Override
    public Mono<Long> getTotalElementForPagination(int pageSize, String name) {
        return getCountByNameLike(name)
                .map(totalElements -> {
                    return (long) Math.ceil((double) totalElements / pageSize);
                }
        );
    }
    @Override
    public Mono<Long> getCountByNameLike(String name) {
        return ownerService.getAuthenticatedUserId()
                .flatMap(userId -> productRepository.countByUserIdAndNameContaining(userId, name));
    }
}