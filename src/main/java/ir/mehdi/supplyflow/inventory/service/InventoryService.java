package ir.mehdi.supplyflow.inventory.service;

import ir.mehdi.supplyflow.catalog.entity.Product;
import ir.mehdi.supplyflow.catalog.exception.ProductNotFoundException;
import ir.mehdi.supplyflow.catalog.repository.ProductRepository;
import ir.mehdi.supplyflow.inventory.dto.ChangeQuantityDTO;
import ir.mehdi.supplyflow.inventory.entity.Inventory;
import ir.mehdi.supplyflow.inventory.enums.InventoryType;
import ir.mehdi.supplyflow.inventory.exception.InsufficientStockException;
import ir.mehdi.supplyflow.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Inventory changeQuantity(ChangeQuantityDTO dto) {

        Product product = productRepository.findById(dto.getProductId()).orElseThrow(() ->
                        new ProductNotFoundException(dto.getProductId()));

        Inventory inventory = inventoryRepository.findByProduct_Id(dto.getProductId()).orElseGet(() -> {
                    if (dto.getType() == InventoryType.DECREASE) {
                        throw new InsufficientStockException(0, dto.getQuantity());
                    }

                    Inventory newInventory = new Inventory();
                    newInventory.setProduct(product);
                    newInventory.setQuantityAvailable(0);
                    return newInventory;
                });

        int currentQuantity = inventory.getQuantityAvailable();

        if (dto.getType() == InventoryType.INCREASE) {

            inventory.setQuantityAvailable(currentQuantity + dto.getQuantity());

        } else if (dto.getType() == InventoryType.DECREASE) {

            if (dto.getQuantity() > currentQuantity) {
                throw new InsufficientStockException(currentQuantity, dto.getQuantity());
            }

            inventory.setQuantityAvailable(currentQuantity - dto.getQuantity());
        }

        return inventoryRepository.save(inventory);
    }
}