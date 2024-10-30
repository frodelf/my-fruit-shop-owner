package test.myfruitshopowner.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import test.myfruitshopowner.entity.enums.StatusHistory;

@Data
@Table("history")
public class History {
    @Id
    private Long id;
    private StatusHistory status;
    @Column("product_id")
    private Long productId;
    @Column("number_of_product")
    private Long numberOfProduct;
    private Long userId;
}