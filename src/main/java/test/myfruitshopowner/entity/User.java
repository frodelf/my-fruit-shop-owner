package test.myfruitshopowner.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import test.myfruitshopowner.entity.enums.DType;

@Table("user")
@Data
public abstract class User {
    @Id
    private Long id;
    private DType dtype;
    @Column("last_name")
    private String lastName;
    private String name;
    @Column("middle_name")
    private String middleName;
    private String phone;
    private String email;
    private String telegram;
    private String password;
}