package com.database.mysql.productManagement.service;

import com.database.mysql.productManagement.entity.Product;
import com.database.mysql.productManagement.repository.ProductManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductManagementRepository repository;

    public ProductServiceImpl(ProductManagementRepository productManagementRepository) {
        repository = productManagementRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product updateProduct(Product product, int id) {
        repository.save(product);
        return product;
    }

    @Override
    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "Success";
    }

    @Override
    public boolean productExistsById(int id) {
        return repository.existsById(id);
    }
}
