package ir.mehdi.supplyflow.catalog.mapper;

import ir.mehdi.supplyflow.catalog.dto.AddProductDTO;
import ir.mehdi.supplyflow.catalog.dto.UpdateProductDTO;
import ir.mehdi.supplyflow.catalog.dto.ViewProductDTO;
import ir.mehdi.supplyflow.catalog.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(AddProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setUnitPrice(dto.getUnitPrice());
        product.setDescription(dto.getDescription());
        product.setUnitOfMeasure(dto.getUnitOfMeasure());

        return product;
    }

    public void updateEntity(Product product, UpdateProductDTO dto) {
        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setUnitPrice(dto.getUnitPrice());
        product.setDescription(dto.getDescription());
        product.setUnitOfMeasure(dto.getUnitOfMeasure());
    }

    public ViewProductDTO toViewDTO(Product product) {
        ViewProductDTO dto = new ViewProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setSku(product.getSku());
        dto.setUnitPrice(product.getUnitPrice());
        dto.setDescription(product.getDescription());
        dto.setUnitOfMeasure(product.getUnitOfMeasure());
        dto.setStatus(product.getStatus());
        dto.setCategoryId(product.getCategory().getId());
        dto.setCategoryName(product.getCategory().getName());

        return dto;
    }
}