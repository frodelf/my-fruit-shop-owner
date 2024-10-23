package test.myfruitshopowner.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
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