package com.example.saleappv1.config;

import com.example.saleappv1.model.Category;
import com.example.saleappv1.model.Product;
import com.example.saleappv1.repository.CategoryRepository;
import com.example.saleappv1.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataSeeder(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() > 0) {
            return;
        }

        Category dienThoai = categoryRepository.save(new Category("Điện thoại di động"));
        Category tablet = categoryRepository.save(new Category("Máy tính bảng"));

        productRepository.save(new Product("iPhone 18", "Điện thoại iPhone 18, màn hình 6.9 inch, chip A20 Pro",
                42290000, "/images/iphone-18.jpg", dienThoai));
        productRepository.save(new Product("Samsung Galaxy S24", "Điện thoại Samsung Galaxy S24, màn hình Dynamic AMOLED",
                17990000, "/images/samsung-galaxy-s24.jpg", dienThoai));
        productRepository.save(new Product("iPad Air M4", "Máy tính bảng iPad Air, chip M4, màn hình 10.9 inch",
                14990000, "/images/ipad-air-m4.jpg", tablet));
        productRepository.save(new Product("Xiaomi Pad 8 Pro", "Máy tính bảng Xiaomi Pad 8 Pro, màn hình AMOLED 11 inch",
                12990000, "/images/xiaomi-pad-8-pro.jpg", tablet));
    }
}