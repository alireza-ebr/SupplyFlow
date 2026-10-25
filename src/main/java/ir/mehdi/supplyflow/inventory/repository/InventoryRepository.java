package ir.mehdi.supplyflow.inventory.repository;

import ir.mehdi.supplyflow.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
