package ir.mehdi.supplyflow.catalog.service;

import ir.mehdi.supplyflow.catalog.dto.product.AddProductDTO;
import ir.mehdi.supplyflow.catalog.dto.product.UpdateProductDTO;
import ir.mehdi.supplyflow.catalog.dto.product.ViewProductDTO;
import ir.mehdi.supplyflow.catalog.entity.Category;
import ir.mehdi.supplyflow.catalog.entity.Product;
import ir.mehdi.supplyflow.catalog.exception.CategoryNotFoundException;
import ir.mehdi.supplyflow.catalog.exception.ProductNotFoundException;
import ir.mehdi.supplyflow.catalog.mapper.ProductMapper;
import ir.mehdi.supplyflow.catalog.repository.CategoryRepository;
import ir.mehdi.supplyflow.catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ViewProductDTO save(AddProductDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(dto.getCategoryId()));

        Product product = productMapper.toEntity(dto);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return productMapper.toViewDTO(savedProduct);
    }

    public List<ViewProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toViewDTO)
                .toList();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public ViewProductDTO viewById(Long id) {
        Product product = findById(id);
        return productMapper.toViewDTO(product);
    }

    public ViewProductDTO update(Long id, UpdateProductDTO dto) {
        Product product = findById(id);

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(dto.getCategoryId()));

        productMapper.updateEntity(product, dto);
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        return productMapper.toViewDTO(updatedProduct);
    }

    public void deleteById(Long id) {
        findById(id);
        productRepository.deleteById(id);
    }
}