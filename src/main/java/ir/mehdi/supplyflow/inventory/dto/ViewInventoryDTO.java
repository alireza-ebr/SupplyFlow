package ir.mehdi.supplyflow.inventory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewInventoryDTO {

    private Long id;
    private Long productId;
    private String productName;
    private Integer quantityAvailable;
}
