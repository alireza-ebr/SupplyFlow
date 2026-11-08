package ir.mehdi.supplyflow.inventory.exception;

import ir.mehdi.supplyflow.exception.ErrorMessage;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(Integer available, Integer requested) {
        super(String.format(ErrorMessage.INSUFFICIENT_STOCK, available, requested));
    }
}