package com.example.saleappv1.service;

import jakarta.annotation.PostConstruct;
import tools.jackson.databind.ObjectMapper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.saleappv1.model.Category;
import com.example.saleappv1.model.Product;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
	private List<Product> products;
    private List<Category> categories;

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream productStream = new ClassPathResource("data/products.json").getInputStream();
             InputStream categoryStream = new ClassPathResource("data/categories.json").getInputStream()) {

            products = Arrays.asList(mapper.readValue(productStream, Product[].class));
            categories = Arrays.asList(mapper.readValue(categoryStream, Category[].class));

        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc dữ liệu JSON", e);
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    public Optional<Product> findById(Long productId) {
        return products.stream()
                .filter(p -> p.getId() == productId)
                .findFirst();
    }

    public String getCategoryName(int categoryId) {
        return categories.stream()
                .filter(c -> c.getId() == categoryId)
                .map(Category::getName)
                .findFirst()
                .orElse("Không xác định");
    }

    public List<Product> filterProducts(Long categoryId, String keyword, Double fromPrice, Double toPrice) {
        return products.stream()
        		.filter(p -> categoryId == null
                || (p.getCategory() != null && p.getCategory().getId().equals(categoryId)))
                .filter(p -> keyword == null || keyword.isBlank()
                        || p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .filter(p -> fromPrice == null || p.getPrice() >= fromPrice)
                .filter(p -> toPrice == null || p.getPrice() <= toPrice)
                .collect(Collectors.toList());
    }
}
