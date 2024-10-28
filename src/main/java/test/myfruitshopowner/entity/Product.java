package test.myfruitshopowner.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table("product")
public class Product {
    @Id
    private Long id;
    private String name;
    private Long number;
    @Column("price_per_piece")
    private BigDecimal pricePerPiece;
    private Long userId;
}