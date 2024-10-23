//package test.myfruitshopowner.repository;
//
//import org.springframework.data.r2dbc.repository.R2dbcRepository;
//import org.springframework.stereotype.Repository;
//import reactor.core.publisher.Mono;
//import test.myfruitshopowner.entity.Owner;
//
//@Repository
//public interface OwnerRepository extends R2dbcRepository<Owner, Long> {
//    Mono<Owner> findByEmail(String email);
//}