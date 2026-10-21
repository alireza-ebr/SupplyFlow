package ir.mehdi.supplyflow.catalog.repository;

import ir.mehdi.supplyflow.catalog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}