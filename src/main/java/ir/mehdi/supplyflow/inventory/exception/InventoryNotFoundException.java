package ir.mehdi.supplyflow.inventory.exception;

import ir.mehdi.supplyflow.exception.ResourceNotFoundException;

public class InventoryNotFoundException extends ResourceNotFoundException {

    public InventoryNotFoundException(Long id) {
        super(
                "INVENTORY_NOT_FOUND",
                "Inventory not found with id: " + id
        );
    }
}
