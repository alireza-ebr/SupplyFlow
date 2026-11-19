package ir.mehdi.supplyflow.catalog.service;

import ir.mehdi.supplyflow.catalog.dto.product.AddProductDTO;
import ir.mehdi.supplyflow.catalog.dto.product.ViewProductDTO;
import ir.mehdi.supplyflow.catalog.entity.Product;
import ir.mehdi.supplyflow.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private final ProductService productService;
    private final InventoryService inventoryService;

    @Transactional
    public ViewProductDTO save(AddProductDTO dto) {
        ViewProductDTO savedProduct = productService.save(dto);
        Product product = productService.findById(savedProduct.getId());
        inventoryService.createInventory(product);
        return savedProduct;
    }

    @Transactional
    public void deleteById(Long id) {
        inventoryService.deleteByProductId(id);
        productService.deleteById(id);
    }
}

