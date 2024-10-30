package test.myfruitshopowner.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.auth.UserDetailsImpl;
import test.myfruitshopowner.entity.Owner;
import test.myfruitshopowner.exception.EntityNotFoundException;
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
    public Mono<Owner> save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Mono<Owner> getById(Long id) {
        return ownerRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new EntityNotFoundException("Product with id: " + id + " not found"))
                );
    }

    public Mono<Long> getAuthenticatedUserId() {
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(context -> {
                    Authentication authentication = context.getAuthentication();
                    if (authentication != null && authentication.isAuthenticated()) {
                        Object principal = authentication.getPrincipal();
                        if (principal instanceof UserDetailsImpl) {
                            return Mono.just(((UserDetailsImpl) principal).getId());
                        }
                    }
                    return Mono.empty();
                });
    }
}