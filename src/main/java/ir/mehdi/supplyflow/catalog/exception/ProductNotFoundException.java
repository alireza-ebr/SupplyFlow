package ir.mehdi.supplyflow.catalog.exception;

import ir.mehdi.supplyflow.exception.ResourceNotFoundException;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException(Long id) {
        super(
                "PRODUCT_NOT_FOUND",
                "Product not found with id: " + id
        );
    }
}