package ir.mehdi.supplyflow.catalog.service;

import ir.mehdi.supplyflow.catalog.entity.Category;
import ir.mehdi.supplyflow.catalog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import ir.mehdi.supplyflow.catalog.exception.CategoryNotFoundException;
import ir.mehdi.supplyflow.catalog.mapper.CategoryMapper;
import ir.mehdi.supplyflow.catalog.dto.category.ViewCategoryDTO;
import ir.mehdi.supplyflow.catalog.dto.category.AddCategoryDTO;
import ir.mehdi.supplyflow.catalog.dto.category.UpdateCategoryDTO;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	private final CategoryMapper categoryMapper;

	public ViewCategoryDTO save(AddCategoryDTO dto) {
		Category category = categoryMapper.toEntity(dto);

		Category savedCategory = categoryRepository.save(category);

		return categoryMapper.toViewDTO(savedCategory);
	}

	public List<ViewCategoryDTO> findAll() {
		return categoryRepository.findAll().stream().map(categoryMapper::toViewDTO).toList();
	}

	public Category findById(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
	}

	public ViewCategoryDTO viewById(Long id) {
		Category category = findById(id);
		return categoryMapper.toViewDTO(category);
	}

	public void deleteById(Long id) {
		findById(id);
		categoryRepository.deleteById(id);
	}
	public ViewCategoryDTO update(Long id, UpdateCategoryDTO dto) {
		Category category = findById(id);

		category.setName(dto.getName());

		Category updatedCategory = categoryRepository.save(category);

		return categoryMapper.toViewDTO(updatedCategory);
	}
}