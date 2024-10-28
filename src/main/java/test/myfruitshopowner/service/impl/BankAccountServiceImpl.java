package test.myfruitshopowner.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.entity.BankAccount;
import test.myfruitshopowner.repository.BankAccountRepository;
import test.myfruitshopowner.service.BankAccountService;
@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    @Override
    public Mono<BankAccount> save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
}
