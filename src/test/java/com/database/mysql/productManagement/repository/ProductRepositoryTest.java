package com.database.mysql.productManagement.repository;

import com.database.mysql.productManagement.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductManagementRepository productRepository;
    Product product;
    @BeforeEach
    void setUp(){
        product = new Product(301,"Soap","Fiama",70);
        productRepository.save(product);
    }
    @Test
    void save(){
        Product newProduct = productRepository.save(new Product(302,"Face Wash","Mama Earth",320));
        assertNotNull(newProduct);
    }
    @Test
    void get(){
        List<Product> allProducts = productRepository.findAll();
        assertNotNull(allProducts
        );
    }
    @Test
    void getById(){
        Product fetched = productRepository.findById(301).get();
        assertEquals(product.getProductId(),fetched.getProductId());
    }
    @Test
    void update(){
        Product newProduct = new Product(301,"Face wash","Himalaya",210);
        Product oldProduct = productRepository.findById(301).get();
        oldProduct.setProductId(newProduct.getProductId());
        oldProduct.setProductName(newProduct.getProductName());
        oldProduct.setSellerName(newProduct.getSellerName());
        oldProduct.setProductPrice(newProduct.getProductPrice());
        productRepository.save(oldProduct);
        assertEquals(newProduct,oldProduct);
    }
    @Test
    void delete(){
        product = null;
        productRepository.deleteById(301);
        assertNull(product);
    }
}