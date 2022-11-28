package co.ke.personal.inventory.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StockRequest {
    @NotNull
    private String itemRefId;
    @Positive
    private Integer quantity;
    @NotNull
    String moveType;

}
