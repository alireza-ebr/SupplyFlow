package ir.mehdi.supplyflow.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCategoryDTO {

	@NotBlank
	private String name;

}