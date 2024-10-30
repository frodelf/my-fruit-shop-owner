package test.myfruitshopowner.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.entity.History;

public interface HistoryRepository extends R2dbcRepository<History, Long> {
    Mono<History> findByProductId(Long productId);
}