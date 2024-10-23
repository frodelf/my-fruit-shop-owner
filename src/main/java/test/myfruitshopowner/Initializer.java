package test.myfruitshopowner;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.entity.BankAccount;
import test.myfruitshopowner.entity.Owner;
import test.myfruitshopowner.service.OwnerService;

@Controller
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {
    private final OwnerService ownerService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
            ownerService.count()
                    .flatMap(count -> {
                        if (count == 0) {
                            Owner owner = new Owner();
                            owner.setLastName("Owner");
                            owner.setName("Ivan");
                            owner.setMiddleName("Ivanovich");
                            owner.setPhone("+380931234567");
                            owner.setEmail("ivan@gmail.com");
                            owner.setTelegram("@ivan_tg");
                            owner.setShopName("ЛьонОк");
                            owner.setPassword(passwordEncoder.encode("password"));

                            BankAccount bankAccount = new BankAccount();
                            owner.setBankAccount(bankAccount);
                            return ownerService.save(owner).then();
                        }
                        return Mono.empty();
                    });
    }
}