package com.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

	@NotBlank
	@Size(min = 2, max = 100)
	@Column(unique = true)
	private String name;

	@Size(max = 500)
	private String description;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Product> products;

	// Getters and setters
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public List<Product> getProducts() { return products; }
	public void setProducts(List<Product> products) { this.products = products; }
}