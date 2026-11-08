package ir.mehdi.supplyflow.catalog.exception;

import ir.mehdi.supplyflow.exception.ErrorMessage;
import ir.mehdi.supplyflow.exception.ResourceNotFoundException;

public class CategoryNotFoundException extends ResourceNotFoundException {

	public CategoryNotFoundException(Long id) {
		super(ErrorMessage.CATEGORY_NOT_FOUND_CODE, String.format(ErrorMessage.CATEGORY_NOT_FOUND, id)
		);
	}
}