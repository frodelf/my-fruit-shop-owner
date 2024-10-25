package test.myfruitshopowner.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Orders {
    @Id
    private Long id = Math.abs(UUID.randomUUID().getMostSignificantBits());
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private Long number;
    @ManyToOne
    private Owner owner;
    @ManyToOne
    private Supplier supplier;
}