package ir.mehdi.supplyflow.catalog.mapper;

import ir.mehdi.supplyflow.catalog.dto.category.ViewCategoryDTO;
import ir.mehdi.supplyflow.catalog.entity.Category;
import org.springframework.stereotype.Component;
import ir.mehdi.supplyflow.catalog.dto.category.AddCategoryDTO;

@Component
public class CategoryMapper {

	public ViewCategoryDTO toViewDTO(Category category) {
		ViewCategoryDTO dto = new ViewCategoryDTO();

		dto.setId(category.getId());
		dto.setName(category.getName());

		return dto;
	}

	public Category toEntity(AddCategoryDTO dto) {
		Category category = new Category();
		category.setName(dto.getName());
		return category;
	}

}