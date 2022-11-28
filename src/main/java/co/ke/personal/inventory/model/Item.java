package co.ke.personal.inventory.model;

import co.ke.personal.inventory.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refId;
    private String name;
    private Integer runningQuantity;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate;

    private LocalDateTime created;
    public Item update(Item item) {
        if (!StringUtils.isNullOrEmpty(item.getName()))
            this.name = item.getName();
        if (item.getRunningQuantity() != 0)
            this.runningQuantity = item.getRunningQuantity();
        if (item.getExpireDate() != null)
            this.expireDate = item.getExpireDate();
        return this;
    }
}
