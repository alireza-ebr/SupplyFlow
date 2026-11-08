package ir.mehdi.supplyflow.inventory.exception;

import ir.mehdi.supplyflow.exception.ErrorMessage;

public class InventoryQuantityOverflowException extends RuntimeException {

    public InventoryQuantityOverflowException() {super(ErrorMessage.INVENTORY_QUANTITY_OVERFLOW);
    }
}