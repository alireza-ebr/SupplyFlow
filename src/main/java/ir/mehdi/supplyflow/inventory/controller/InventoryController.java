package ir.mehdi.supplyflow.inventory.controller;

import ir.mehdi.supplyflow.inventory.dto.ChangeQuantityDTO;
import ir.mehdi.supplyflow.inventory.entity.Inventory;
import ir.mehdi.supplyflow.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Inventory> changeQuantity(@Valid @RequestBody ChangeQuantityDTO dto) {
        Inventory inventory = inventoryService.changeQuantity(dto);
        return ResponseEntity.ok(inventory);
    }
}