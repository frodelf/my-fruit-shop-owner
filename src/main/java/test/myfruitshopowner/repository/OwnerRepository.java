package test.myfruitshopowner.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.entity.Owner;

@Repository
public interface OwnerRepository extends ReactiveCrudRepository<Owner, Long> {
//    Mono<Owner> findByEmail(String email);
}