package ir.mehdi.supplyflow.catalog.controller;

import ir.mehdi.supplyflow.catalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ir.mehdi.supplyflow.catalog.dto.category.ViewCategoryDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import ir.mehdi.supplyflow.catalog.dto.category.AddCategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.validation.Valid;
import ir.mehdi.supplyflow.catalog.dto.category.UpdateCategoryDTO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping("/{id}")
	public ViewCategoryDTO findById(@PathVariable Long id) {
		return categoryService.viewById(id);
	}

	@GetMapping
	public List<ViewCategoryDTO> findAll() {
		return categoryService.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ViewCategoryDTO save(@Valid @RequestBody AddCategoryDTO dto) {
		return categoryService.save(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ViewCategoryDTO> update(
			@PathVariable Long id,
			@Valid @RequestBody UpdateCategoryDTO dto) {

		return ResponseEntity.ok(categoryService.update(id, dto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categoryService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}