package ir.mehdi.supplyflow.catalog.dto.product;

import ir.mehdi.supplyflow.catalog.enums.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ViewProductDTO {

    private Long id;
    private String name;
    private String sku;
    private BigDecimal unitPrice;
    private String description;
    private String unitOfMeasure;
    private ProductStatus status;
    private Long categoryId;
    private String categoryName;
}