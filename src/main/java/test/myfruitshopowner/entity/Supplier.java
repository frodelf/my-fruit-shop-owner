package test.myfruitshopowner.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Supplier extends User{
    @Column(length = 100)
    private String companyName;
}