package test.myfruitshopowner.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import test.myfruitshopowner.entity.BankAccount;

public interface BankAccountRepository extends R2dbcRepository<BankAccount, Long> {
}