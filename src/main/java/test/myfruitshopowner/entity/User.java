package test.myfruitshopowner.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    private Long id = Math.abs(UUID.randomUUID().getMostSignificantBits());
    @Column(length = 100)
    private String lastName;
    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String middleName;
    @Column(length = 100)
    private String phone;
    @Column(length = 100)
    private String email;
    @Column(length = 100)
    private String telegram;
    @Column(length = 100, nullable = false)
    private String password;
}