package ir.mehdi.supplyflow.inventory.exception;

import ir.mehdi.supplyflow.exception.ErrorMessage;

public class InventoryAlreadyExistsException extends RuntimeException {

    public InventoryAlreadyExistsException(Long productId) {
        super(String.format(ErrorMessage.INVENTORY_ALREADY_EXISTS, productId));
    }
}