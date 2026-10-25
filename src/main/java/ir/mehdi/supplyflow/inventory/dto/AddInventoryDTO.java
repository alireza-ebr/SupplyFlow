package ir.mehdi.supplyflow.inventory.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddInventoryDTO {

    @NotNull
    @Positive
    private Long productId;

    @NotNull
    @PositiveOrZero
    private Integer quantityAvailable;
}
