package test.myfruitshopowner.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import test.myfruitshopowner.entity.enums.DType;

@Data
@Table("user")
@EqualsAndHashCode(callSuper = true)
public class Supplier extends User{
    @Column("company_name")
    private String companyName;
    public Supplier() {
        this.setDtype(DType.SUPPLIER);
    }
}