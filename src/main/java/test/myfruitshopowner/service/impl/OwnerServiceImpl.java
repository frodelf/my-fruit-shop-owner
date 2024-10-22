package test.myfruitshopowner.service.impl;

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
//    private final OwnerRepository ownerRepository;
    @Override
    public Mono<Owner> getByEmailForAuth(String username) {
        return null;
    }
//        return ownerRepository.findByEmail(username)
//                .switchIfEmpty(
//                        Mono.error(new AuthenticationCredentialsNotFoundException("Credential isn't correct"))
//                );
//    }
}