package test.myfruitshopowner.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
public class Product {
    @Id
    private Long id = Math.abs(UUID.randomUUID().getMostSignificantBits());
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private Long number;
    @Column(nullable = false)
    private BigDecimal pricePerPiece;
    @ManyToOne
    private User user;
}