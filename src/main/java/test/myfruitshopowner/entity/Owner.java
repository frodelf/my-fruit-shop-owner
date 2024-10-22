package test.myfruitshopowner.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Owner extends User{
    @Column(nullable = false, length = 100)
    private String shopName;
    @OneToOne
    private BankAccount bankAccount;
}