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
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getErrorCode(),
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficientStock(
            InsufficientStockException ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                HttpStatus.CONFLICT,
                HttpStatus.CONFLICT.getReasonPhrase(),
                ErrorMessage.INSUFFICIENT_STOCK_CODE,
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(InvalidInventoryQuantityException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidInventoryQuantity(
            InvalidInventoryQuantityException ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ErrorMessage.INVALID_INVENTORY_QUANTITY_CODE,
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(InventoryQuantityOverflowException.class)
    public ResponseEntity<Map<String, Object>> handleInventoryQuantityOverflow(
            InventoryQuantityOverflowException ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                HttpStatus.CONFLICT,
                HttpStatus.CONFLICT.getReasonPhrase(),
                ErrorMessage.INVENTORY_QUANTITY_OVERFLOW_CODE,
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(InventoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleInventoryAlreadyExists(
            InventoryAlreadyExistsException ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                HttpStatus.CONFLICT,
                HttpStatus.CONFLICT.getReasonPhrase(),
                ErrorMessage.INVENTORY_ALREADY_EXISTS_CODE,
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationError(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining("، "));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "درخواست نامعتبر است");
        body.put("message", message);
        body.put("path", request.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(
            HttpStatus status,
            String error,
            String errorCode,
            String message,
            HttpServletRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", error);

        if (errorCode != null) {
            body.put("errorCode", errorCode);
        }

        body.put("message", message);
        body.put("path", request.getRequestURI());

        return ResponseEntity.status(status).body(body);
    }
}