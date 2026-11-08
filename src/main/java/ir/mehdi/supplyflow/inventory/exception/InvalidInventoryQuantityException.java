package ir.mehdi.supplyflow.inventory.exception;

import ir.mehdi.supplyflow.exception.ErrorMessage;

public class InvalidInventoryQuantityException extends RuntimeException {

    public InvalidInventoryQuantityException(Integer quantity) {
        super(String.format(ErrorMessage.INVALID_INVENTORY_QUANTITY, quantity));
    }
}