package test.myfruitshopowner.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table("bank_account")
public class BankAccount {
    @Id
    private Long id;
    private BigDecimal balance = BigDecimal.ZERO;
}