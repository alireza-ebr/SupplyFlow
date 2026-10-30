package ir.mehdi.supplyflow.inventory.repository;

import ir.mehdi.supplyflow.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProduct_Id(Long productId);
}
