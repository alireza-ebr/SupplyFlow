package ir.mehdi.supplyflow.catalog.controller;

import ir.mehdi.supplyflow.catalog.dto.AddProductDTO;
import ir.mehdi.supplyflow.catalog.dto.UpdateProductDTO;
import ir.mehdi.supplyflow.catalog.dto.ViewProductDTO;
import ir.mehdi.supplyflow.catalog.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ViewProductDTO> save(
            @Valid @RequestBody AddProductDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ViewProductDTO>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewProductDTO> findById(
            @PathVariable Long id) {
        return ResponseEntity.ok(productService.viewById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViewProductDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductDTO dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}