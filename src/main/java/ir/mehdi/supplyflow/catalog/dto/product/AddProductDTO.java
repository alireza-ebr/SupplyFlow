package ir.mehdi.supplyflow.catalog.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddProductDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String sku;

    @NotNull
    @Positive
    private BigDecimal unitPrice;

    private String description;

    @NotBlank
    private String unitOfMeasure;

    @NotNull
    @Positive
    private Long categoryId;
}