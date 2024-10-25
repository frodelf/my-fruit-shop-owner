package test.myfruitshopowner.entity;

import jakarta.persistence.*;
import lombok.Data;
import test.myfruitshopowner.entity.enums.StatusHistory;

@Entity
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private StatusHistory status;
    @Column(length = 1000, nullable = false)
    private String message;
    @ManyToOne
    private User user;
}