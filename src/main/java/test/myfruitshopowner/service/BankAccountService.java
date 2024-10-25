package test.myfruitshopowner.service;

import reactor.core.publisher.Mono;
import test.myfruitshopowner.entity.BankAccount;

public interface BankAccountService {
    Mono<BankAccount> save(BankAccount bankAccount);
}