package ir.mehdi.supplyflow.inventory.dto;

import ir.mehdi.supplyflow.inventory.enums.InventoryType;
import java.time.LocalDateTime;

public record InventoryTransactionDTO(
        Long id,
        InventoryType type,
        Integer quantity,
        Integer previousQuantity,
        Integer newQuantity,
        LocalDateTime createdAt
) {
}
