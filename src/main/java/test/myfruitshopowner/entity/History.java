package test.myfruitshopowner.entity;

import jakarta.persistence.*;
import lombok.Data;
import test.myfruitshopowner.entity.enums.StatusHistory;

import java.util.UUID;

@Entity
@Data
public class History {
    @Id
    private Long id = Math.abs(UUID.randomUUID().getMostSignificantBits());
    @Column(nullable = false)
    private StatusHistory status;
    @Column(length = 1000, nullable = false)
    private String message;
    @ManyToOne
    private User user;
}