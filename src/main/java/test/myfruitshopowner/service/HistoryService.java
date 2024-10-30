package test.myfruitshopowner.service;

import reactor.core.publisher.Mono;

public interface HistoryService {
    Mono<String> getLastByProductId(Long productId);
}