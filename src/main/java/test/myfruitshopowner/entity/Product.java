package test.myfruitshopowner.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private Long number;
    @Column(nullable = false)
    private BigDecimal pricePerPiece;
    @ManyToOne
    private User user;
}