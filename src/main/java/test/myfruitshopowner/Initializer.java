package test.myfruitshopowner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.entity.BankAccount;
import test.myfruitshopowner.entity.Owner;
import test.myfruitshopowner.service.BankAccountService;
import test.myfruitshopowner.service.OwnerService;

@Controller
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {

    private final OwnerService ownerService;
    private final PasswordEncoder passwordEncoder;
    private final BankAccountService bankAccountService;

    @Override
    public void run(String... args) {
        ownerService.count()
                .flatMap(count -> {
                    if (count == 0) {
                        BankAccount bankAccount = new BankAccount();
                        return bankAccountService.save(bankAccount)
                                .flatMap(savedBankAccount -> {
                                    Owner owner = new Owner();
                                    owner.setLastName("Owner");
                                    owner.setName("Ivan");
                                    owner.setMiddleName("Ivanovich");
                                    owner.setPhone("+380931234567");
                                    owner.setEmail("ivan@gmail.com");
                                    owner.setTelegram("@ivan_tg");
                                    owner.setShopName("ЛьонОк");
//                                    owner.setPassword(passwordEncoder.encode("password"));
                                    owner.setPassword(passwordEncoder.encode("password"));
                                    owner.setBankAccountId(savedBankAccount.getId());
                                    return ownerService.save(owner);
                                }).then();
                    }
                    return Mono.empty();
                })
                .subscribe();
    }
}