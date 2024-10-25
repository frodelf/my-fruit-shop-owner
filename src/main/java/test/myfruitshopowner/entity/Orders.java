package test.myfruitshopowner.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("orders")
public class Orders {
    @Id
    @Transient
    private Long id;
    private Product product;
    private Long number;
    private Owner owner;
    private Long supplierId;
}