
package ir.mehdi.supplyflow.inventory.service;

import ir.mehdi.supplyflow.catalog.entity.Product;
import ir.mehdi.supplyflow.catalog.service.ProductService;
import ir.mehdi.supplyflow.inventory.dto.ChangeQuantityDTO;
import ir.mehdi.supplyflow.inventory.entity.Inventory;
import ir.mehdi.supplyflow.inventory.entity.InventoryTransaction;
import ir.mehdi.supplyflow.inventory.enums.InventoryType;
import ir.mehdi.supplyflow.inventory.exception.InsufficientStockException;
import ir.mehdi.supplyflow.inventory.exception.InventoryQuantityOverflowException;
import ir.mehdi.supplyflow.inventory.exception.InventoryAlreadyExistsException;
import ir.mehdi.supplyflow.inventory.exception.InventoryNotFoundException;
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
    private final ProductService productService;
    private final InventoryTransactionRepository inventoryTransactionRepository;

    @Transactional
    public Inventory changeQuantity(ChangeQuantityDTO dto) {
        Product product = productService.findById(dto.getProductId());

        Inventory inventory = inventoryRepository.findByProduct_Id(dto.getProductId()).orElseGet(() -> {

                    if (dto.getType() == InventoryType.DECREASE) {throw new InsufficientStockException(0, dto.getQuantity());}

            return newInventory(product);
                });

        int currentQuantity = inventory.getQuantityAvailable();
        applyQuantityChange(inventory, dto.getType(), dto.getQuantity());

        Inventory savedInventory = inventoryRepository.save(inventory);

        recordInventoryTransaction(
                savedInventory,
                dto.getType(),
                dto.getQuantity(),
                currentQuantity,
                savedInventory.getQuantityAvailable()
        );

        return savedInventory;
    }

    private void applyQuantityChange(Inventory inventory, InventoryType type, Integer quantity) {

        int currentQuantity = inventory.getQuantityAvailable();

        if (type == InventoryType.INCREASE) {
            try {
                inventory.setQuantityAvailable(
                        Math.addExact(currentQuantity, quantity)
                );
            } catch (ArithmeticException e) {
                throw new InventoryQuantityOverflowException();
            }

        } else if (type == InventoryType.DECREASE) {
            if (quantity > currentQuantity) {
                throw new InsufficientStockException(currentQuantity, quantity);
            }

            inventory.setQuantityAvailable(currentQuantity - quantity);
        }
    }

    private Inventory newInventory(Product product) {
        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantityAvailable(0);
        return inventory;
    }

    private void recordInventoryTransaction(
            Inventory inventory,
            InventoryType type,
            int quantity,
            int previousQuantity,
            int newQuantity) {
        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setInventory(inventory);
        transaction.setType(type);
        transaction.setQuantity(quantity);
        transaction.setPreviousQuantity(previousQuantity);
        transaction.setNewQuantity(newQuantity);

        inventoryTransactionRepository.save(transaction);
    }

    @Transactional
    public Inventory createInventory(Product product) {

        inventoryRepository.findByProduct_Id(product.getId()).ifPresent(inventory -> {
                    throw new InventoryAlreadyExistsException(product.getId());});

        return inventoryRepository.save(newInventory(product));
    }

    @Transactional
    public void deleteByProductId(Long productId) {

        Inventory inventory = inventoryRepository.findByProduct_Id(productId).orElse(null);

        if (inventory != null) {inventoryRepository.delete(inventory);
        }
    }

    @Transactional(readOnly = true)
    public List<InventoryTransactionDTO> getInventoryHistory(Long productId) {

        Inventory inventory = inventoryRepository.findByProduct_Id(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));

        return inventoryTransactionRepository
                .findByInventory_IdOrderByCreatedAtDesc(inventory.getId())
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private InventoryTransactionDTO toDTO(InventoryTransaction transaction) {
        return new InventoryTransactionDTO(
                transaction.getId(),
                transaction.getType(),
                transaction.getQuantity(),
                transaction.getPreviousQuantity(),
                transaction.getNewQuantity(),
                transaction.getCreatedAt()
        );
    }
}
