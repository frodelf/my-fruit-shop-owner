package test.myfruitshopowner.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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