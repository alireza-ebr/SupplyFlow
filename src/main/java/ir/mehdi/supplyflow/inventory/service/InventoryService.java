
package ir.mehdi.supplyflow.inventory.service;

import ir.mehdi.supplyflow.catalog.entity.Product;
import ir.mehdi.supplyflow.catalog.exception.ProductNotFoundException;
import ir.mehdi.supplyflow.catalog.repository.ProductRepository;
import ir.mehdi.supplyflow.inventory.dto.ChangeQuantityDTO;
import ir.mehdi.supplyflow.inventory.entity.Inventory;
import ir.mehdi.supplyflow.inventory.entity.InventoryTransaction;
import ir.mehdi.supplyflow.inventory.enums.InventoryType;
import ir.mehdi.supplyflow.inventory.exception.InsufficientStockException;
import ir.mehdi.supplyflow.inventory.exception.InventoryQuantityOverflowException;
import ir.mehdi.supplyflow.inventory.exception.InventoryAlreadyExistsException;
import ir.mehdi.supplyflow.inventory.exception.InvalidInventoryQuantityException;
import ir.mehdi.supplyflow.inventory.repository.InventoryRepository;
import ir.mehdi.supplyflow.inventory.repository.InventoryTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ir.mehdi.supplyflow.inventory.dto.InventoryTransactionDTO;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;

    @Transactional
    public Inventory changeQuantity(ChangeQuantityDTO dto) {

        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            throw new InvalidInventoryQuantityException(dto.getQuantity());
        }

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException(dto.getProductId()));

        Inventory inventory = inventoryRepository
                .findByProduct_Id(dto.getProductId())
                .orElseGet(() -> {

                    if (dto.getType() == InventoryType.DECREASE) {
                        throw new InsufficientStockException(
                                0, dto.getQuantity());
                    }

                    Inventory newInventory = new Inventory();
                    newInventory.setProduct(product);
                    newInventory.setQuantityAvailable(0);

                    return newInventory;
                });

        int currentQuantity = inventory.getQuantityAvailable();

        if (dto.getType() == InventoryType.INCREASE) {

            try {
                inventory.setQuantityAvailable(
                        Math.addExact(currentQuantity, dto.getQuantity()));

            } catch (ArithmeticException e) {
                throw new InventoryQuantityOverflowException();
            }

        } else if (dto.getType() == InventoryType.DECREASE) {

            if (dto.getQuantity() > currentQuantity) {
                throw new InsufficientStockException(
                        currentQuantity, dto.getQuantity());
            }

            inventory.setQuantityAvailable(
                    currentQuantity - dto.getQuantity());
        }

        Inventory savedInventory = inventoryRepository.save(inventory);

        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setInventory(savedInventory);
        transaction.setType(dto.getType());
        transaction.setQuantity(dto.getQuantity());
        transaction.setPreviousQuantity(currentQuantity);
        transaction.setNewQuantity(savedInventory.getQuantityAvailable());

        inventoryTransactionRepository.save(transaction);

        return savedInventory;
    }

    @Transactional
    public Inventory createInventory(Product product) {

        inventoryRepository.findByProduct_Id(product.getId())
                .ifPresent(inventory -> {
                    throw new InventoryAlreadyExistsException(product.getId());
                });

        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantityAvailable(0);

        return inventoryRepository.save(inventory);
    }

    @Transactional
    public void deleteByProductId(Long productId) {

        Inventory inventory = inventoryRepository
                .findByProduct_Id(productId)
                .orElse(null);

        if (inventory != null) {
            inventoryRepository.delete(inventory);
        }
    }

    @Transactional(readOnly = true)
    public List<InventoryTransactionDTO> getInventoryHistory(Long productId) {

        Inventory inventory = inventoryRepository.findByProduct_Id(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(productId));

        return inventoryTransactionRepository
                .findByInventory_IdOrderByCreatedAtDesc(inventory.getId())
                .stream()
                .map(transaction -> new InventoryTransactionDTO(
                        transaction.getId(),
                        transaction.getType(),
                        transaction.getQuantity(),
                        transaction.getPreviousQuantity(),
                        transaction.getNewQuantity(),
                        transaction.getCreatedAt()
                ))
                .toList();
    }
}
