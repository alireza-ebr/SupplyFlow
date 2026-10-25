package ir.mehdi.supplyflow.inventory.mapper;

import ir.mehdi.supplyflow.inventory.dto.AddInventoryDTO;
import ir.mehdi.supplyflow.inventory.dto.UpdateInventoryDTO;
import ir.mehdi.supplyflow.inventory.dto.ViewInventoryDTO;
import ir.mehdi.supplyflow.inventory.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public Inventory toEntity(AddInventoryDTO dto) {
        Inventory inventory = new Inventory();
        inventory.setQuantityAvailable(dto.getQuantityAvailable());
        return inventory;
    }

    public void updateEntity(
            Inventory inventory,
            UpdateInventoryDTO dto) {
        inventory.setQuantityAvailable(dto.getQuantityAvailable());
    }

    public ViewInventoryDTO toViewDTO(Inventory inventory) {
        ViewInventoryDTO dto = new ViewInventoryDTO();
        dto.setId(inventory.getId());
        dto.setProductId(inventory.getProduct().getId());
        dto.setProductName(inventory.getProduct().getName());
        dto.setQuantityAvailable(inventory.getQuantityAvailable());
        return dto;
    }
}
