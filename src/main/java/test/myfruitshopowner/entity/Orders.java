package test.myfruitshopowner.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("orders")
public class Orders {
    @Id
    private Long id;
    private Product product;
    private Long number;
    private Long owner_id;
    private Long supplierId;
}