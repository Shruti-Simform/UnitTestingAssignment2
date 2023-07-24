package com.database.mysql.productManagement.service;

import com.database.mysql.productManagement.entity.Product;
import com.database.mysql.productManagement.repository.ProductManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {
    @Mock
    private ProductManagementRepository productManagementRepository;
    private ProductService productService;
    AutoCloseable autoCloseable;
    Product product;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productManagementRepository);
        product = new Product(301,"Soap","Fiama",70);
    }
    @Test
    void testCreateProduct(){
        mock(Product.class);
        mock(ProductManagementRepository.class);

        when(productManagementRepository.save(product)).thenReturn(product);
        assertThat(productService.createProduct(product)).isEqualTo(product);
    }
    @Test
    void testUpdateProduct(){
        mock(Product.class);
        mock(ProductManagementRepository.class);

        when(productManagementRepository.save(product)).thenReturn(product);
        assertThat(productService.updateProduct(product,product.getProductId())).isEqualTo(product);
    }
    @Test
    void testGetProductById(){
        mock(Product.class);
        mock(ProductManagementRepository.class);

        when(productManagementRepository.findById(product.getProductId())).thenReturn(Optional.ofNullable(product));
        assertThat(productService.updateProduct(product,product.getProductId())).isEqualTo(product);
        assertThat(productService.getById(301).getProductName()).isEqualTo(product.getProductName());
    }
    @Test
    void testGetProducts(){
        mock(Product.class);
        mock(ProductManagementRepository.class);

        when(productManagementRepository.findAll()).thenReturn(new ArrayList<Product>(Collections.singleton(product)));
        assertThat(productService.getAllProducts().get(0).getSellerName()).isEqualTo(product.getSellerName());
    }
    @Test
    void testDeleteProducts(){
        mock(Product.class);
        mock(ProductManagementRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(productManagementRepository).deleteById(any());
        assertThat(productService.deleteProduct(301)).isEqualTo("Success");
    }
}