package ir.mehdi.supplyflow.inventory.dto;

import ir.mehdi.supplyflow.inventory.enums.InventoryType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeQuantityDTO {

    @NotNull
    @Positive
    private Long productId;

    @NotNull
    private InventoryType type;

    @NotNull
    @Positive
    private Integer quantity;
}