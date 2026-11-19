package ir.mehdi.supplyflow.inventory.exception;

import ir.mehdi.supplyflow.exception.ErrorMessage;
import ir.mehdi.supplyflow.exception.ResourceNotFoundException;

public class InventoryNotFoundException extends ResourceNotFoundException {

    public InventoryNotFoundException(Long productId) {
        super(
                ErrorMessage.INVENTORY_NOT_FOUND_CODE,
                String.format(ErrorMessage.INVENTORY_NOT_FOUND, productId)
        );
    }
}
