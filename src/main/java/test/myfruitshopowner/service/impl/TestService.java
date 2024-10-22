package test.myfruitshopowner.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import test.myfruitshopowner.repository.OwnerRepository;

@Service
@RequiredArgsConstructor
public class TestService {
    private final OwnerRepository ownerRepository;
}