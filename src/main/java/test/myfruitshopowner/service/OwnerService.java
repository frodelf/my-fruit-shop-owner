package test.myfruitshopowner.service;

import reactor.core.publisher.Mono;
import test.myfruitshopowner.entity.Owner;

public interface OwnerService {
    Mono<Owner> getByEmailForAuth(String username);
    Mono<Long> count();
    Mono<Owner> save(Owner owner);
    Mono<Owner> getById(Long id);
    Mono<Long> getAuthenticatedUserId();
}