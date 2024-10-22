package test.myfruitshopowner.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private Long number;
    @ManyToOne
    private Owner owner;
    @ManyToOne
    private Supplier supplier;
}