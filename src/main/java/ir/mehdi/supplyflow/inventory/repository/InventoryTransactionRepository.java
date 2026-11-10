package ir.mehdi.supplyflow.inventory.repository;

import ir.mehdi.supplyflow.inventory.entity.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryTransactionRepository
        extends JpaRepository<InventoryTransaction, Long> {

    List<InventoryTransaction> findByInventory_IdOrderByCreatedAtDesc(Long inventoryId);
}