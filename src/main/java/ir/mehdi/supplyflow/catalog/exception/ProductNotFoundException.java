package ir.mehdi.supplyflow.catalog.exception;

import ir.mehdi.supplyflow.exception.ErrorMessage;
import ir.mehdi.supplyflow.exception.ResourceNotFoundException;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException(Long id) {
        super(ErrorMessage.PRODUCT_NOT_FOUND_CODE, String.format(ErrorMessage.PRODUCT_NOT_FOUND, id)
        );
    }
}