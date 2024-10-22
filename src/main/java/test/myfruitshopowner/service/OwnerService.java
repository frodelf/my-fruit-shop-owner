package test.myfruitshopowner.service;

import reactor.core.publisher.Mono;
import test.myfruitshopowner.entity.Owner;

public interface OwnerService {
    Mono<Owner> getByEmailForAuth(String username);
}