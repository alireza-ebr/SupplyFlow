package ir.mehdi.supplyflow.exception;

public interface ErrorMessage {

    // Inventory error codes
    String INSUFFICIENT_STOCK_CODE = "INSUFFICIENT_STOCK";
    String INVALID_INVENTORY_QUANTITY_CODE = "INVALID_INVENTORY_QUANTITY";
    String INVENTORY_QUANTITY_OVERFLOW_CODE = "INVENTORY_QUANTITY_OVERFLOW";
    String INVENTORY_ALREADY_EXISTS_CODE = "INVENTORY_ALREADY_EXISTS";

    // Catalog error codes
    String PRODUCT_NOT_FOUND_CODE = "PRODUCT_NOT_FOUND";
    String CATEGORY_NOT_FOUND_CODE = "CATEGORY_NOT_FOUND";

    // Inventory error messages
    String INSUFFICIENT_STOCK = "Insufficient stock. Available: %d, requested: %d";
    String INVALID_INVENTORY_QUANTITY = "Inventory quantity must be greater than zero. Given: %d";
    String INVENTORY_QUANTITY_OVERFLOW = "Inventory quantity exceeds the maximum allowed value.";
    String INVENTORY_ALREADY_EXISTS = "Inventory already exists for product ID: %d";

    // Catalog error messages
    String PRODUCT_NOT_FOUND = "Product not found with id: %d";
    String CATEGORY_NOT_FOUND = "Category not found with id: %d";
}