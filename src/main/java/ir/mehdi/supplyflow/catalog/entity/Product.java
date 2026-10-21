package ir.mehdi.supplyflow.catalog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import ir.mehdi.supplyflow.catalog.enums.ProductStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String sku;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ProductStatus status = ProductStatus.ACTIVE;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal unitPrice;

	private String description;

	private String unitOfMeasure;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

}