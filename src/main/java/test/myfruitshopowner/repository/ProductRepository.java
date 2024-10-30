package test.myfruitshopowner.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.entity.Product;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findAllByUserIdAndNameContaining(Long userId, String name, Pageable pageable);
    Mono<Long> countByUserIdAndNameContaining(Long authenticatedUserId, String name);
}