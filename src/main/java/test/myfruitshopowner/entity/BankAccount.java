package test.myfruitshopowner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
public class BankAccount {
    @Id
    private Long id = Math.abs(UUID.randomUUID().getMostSignificantBits());
    private BigDecimal balance = BigDecimal.ZERO;
}