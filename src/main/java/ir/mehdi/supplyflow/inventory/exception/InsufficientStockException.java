package ir.mehdi.supplyflow.inventory.exception;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(Integer available, Integer requested) {
        super("Insufficient stock. Available: " + available
                + ", requested: " + requested);
    }
}