package test.myfruitshopowner.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.entity.Owner;
import test.myfruitshopowner.repository.OwnerRepository;
import test.myfruitshopowner.service.OwnerService;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    @Override
    public Mono<Owner> getByEmailForAuth(String username) {
        return ownerRepository.findByEmail(username)
                .switchIfEmpty(
                        Mono.error(new AuthenticationCredentialsNotFoundException("Credential isn't correct"))
                );
    }
    @Override
    public Mono<Long> count() {
        return ownerRepository.count();
    }
    @Override
    @Transactional
    public Mono<Owner> save(Owner owner) {
        return ownerRepository.save(owner);
    }
}