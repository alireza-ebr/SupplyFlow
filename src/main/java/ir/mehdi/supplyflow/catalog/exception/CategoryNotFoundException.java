package ir.mehdi.supplyflow.catalog.exception;

import ir.mehdi.supplyflow.exception.ResourceNotFoundException;

public class CategoryNotFoundException extends ResourceNotFoundException {

	public CategoryNotFoundException(Long id) {
		super(
				"CATEGORY_NOT_FOUND",
				"Category not found with id: " + id
		);
	}
}