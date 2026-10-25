package ir.mehdi.supplyflow.inventory.controller;

import ir.mehdi.supplyflow.inventory.dto.AddInventoryDTO;
import ir.mehdi.supplyflow.inventory.dto.UpdateInventoryDTO;
import ir.mehdi.supplyflow.inventory.dto.ViewInventoryDTO;
import ir.mehdi.supplyflow.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<ViewInventoryDTO> save(
            @Valid @RequestBody AddInventoryDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inventoryService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ViewInventoryDTO>> findAll() {
        return ResponseEntity.ok(inventoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewInventoryDTO> findById(
            @PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.viewById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViewInventoryDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateInventoryDTO dto) {
        return ResponseEntity.ok(inventoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
