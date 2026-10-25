package ir.mehdi.supplyflow.inventory.service;

import ir.mehdi.supplyflow.catalog.entity.Product;
import ir.mehdi.supplyflow.catalog.exception.ProductNotFoundException;
import ir.mehdi.supplyflow.catalog.repository.ProductRepository;
import ir.mehdi.supplyflow.inventory.dto.AddInventoryDTO;
import ir.mehdi.supplyflow.inventory.dto.UpdateInventoryDTO;
import ir.mehdi.supplyflow.inventory.dto.ViewInventoryDTO;
import ir.mehdi.supplyflow.inventory.entity.Inventory;
import ir.mehdi.supplyflow.inventory.exception.InventoryNotFoundException;
import ir.mehdi.supplyflow.inventory.mapper.InventoryMapper;
import ir.mehdi.supplyflow.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final InventoryMapper inventoryMapper;

    public ViewInventoryDTO save(AddInventoryDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException(dto.getProductId()));

        Inventory inventory = inventoryMapper.toEntity(dto);
        inventory.setProduct(product);

        Inventory savedInventory = inventoryRepository.save(inventory);

        return inventoryMapper.toViewDTO(savedInventory);
    }

    public List<ViewInventoryDTO> findAll() {
        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::toViewDTO)
                .toList();
    }

    public Inventory findById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException(id));
    }

    public ViewInventoryDTO viewById(Long id) {
        Inventory inventory = findById(id);
        return inventoryMapper.toViewDTO(inventory);
    }

    public ViewInventoryDTO update(Long id, UpdateInventoryDTO dto) {
        Inventory inventory = findById(id);

        inventoryMapper.updateEntity(inventory, dto);

        Inventory updatedInventory = inventoryRepository.save(inventory);

        return inventoryMapper.toViewDTO(updatedInventory);
    }

    public void deleteById(Long id) {
        findById(id);
        inventoryRepository.deleteById(id);
    }
}
