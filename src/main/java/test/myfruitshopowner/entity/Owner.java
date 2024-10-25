package test.myfruitshopowner.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import test.myfruitshopowner.entity.enums.DType;

@Data
@Table("user")
@EqualsAndHashCode(callSuper = true)
public class Owner extends User{
    @Column("shop_name")
    private String shopName;
    private Long bankAccountId;
    public Owner() {
        this.setDtype(DType.OWNER);
    }
}