package co.ke.personal.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MoveType moveType;

    private LocalDateTime movedDate;
    private Integer quantity;
    private String itemRefId;

    public InventoryMovement(Item item, int quantity){
        this.quantity = quantity;
        this.itemRefId = item.getRefId();
        this.movedDate = LocalDateTime.now();
    }
}
