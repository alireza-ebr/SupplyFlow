package ir.mehdi.supplyflow.catalog.exception;

import ir.mehdi.supplyflow.exception.ResourceNotFoundException;
import ir.mehdi.supplyflow.exception.ErrorMessage;
import ir.mehdi.supplyflow.inventory.exception.InvalidInventoryQuantityException;
import ir.mehdi.supplyflow.inventory.exception.InventoryAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ir.mehdi.supplyflow.inventory.exception.InsufficientStockException;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import ir.mehdi.supplyflow.inventory.exception.InventoryQuantityOverflowException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("errorCode", ex.getErrorCode());
        body.put("message", ex.getMessage());
        body.put("path", request.getRequestURI());

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficientStock(
            InsufficientStockException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("errorCode", ErrorMessage.INSUFFICIENT_STOCK_CODE);
        body.put("path", request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(InvalidInventoryQuantityException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidInventoryQuantity(
            InvalidInventoryQuantityException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("errorCode", ErrorMessage.INVALID_INVENTORY_QUANTITY_CODE);
        body.put("message", ex.getMessage());
        body.put("path", request.getRequestURI());

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(InventoryQuantityOverflowException.class)
    public ResponseEntity<Map<String, Object>> handleInventoryQuantityOverflow(
            InventoryQuantityOverflowException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("errorCode", ErrorMessage.INVENTORY_QUANTITY_OVERFLOW_CODE);
        body.put("message", ex.getMessage());
        body.put("path", request.getRequestURI());

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(InventoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleInventoryAlreadyExists(
            InventoryAlreadyExistsException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("errorCode", ErrorMessage.INVENTORY_ALREADY_EXISTS_CODE);
        body.put("message", ex.getMessage());
        body.put("path", request.getRequestURI());

        return ResponseEntity.status(status).body(body);
    }
}