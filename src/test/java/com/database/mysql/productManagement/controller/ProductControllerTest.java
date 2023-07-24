package com.database.mysql.productManagement.controller;

import com.database.mysql.productManagement.entity.Product;
import com.database.mysql.productManagement.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    Product product1;
    Product product2;
    List<Product> productList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        product1 = new Product(301,"Notebook","Classmate",300);
        product2 = new Product(302,"Pen","Linc",250);
        productList.add(product1);
        productList.add(product2);
    }
    @Test
    void testGetProduct() throws Exception {
        when(productService.getById(301)).thenReturn(product1);
        this.mockMvc.perform(get("/product/get/301")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(productList);
        this.mockMvc.perform(get("/product/get")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void testCreateProduct() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(product1);

        when(productService.createProduct(product1)).thenReturn(product1);
        this.mockMvc.perform(post("/product/create").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void testUpdateProduct() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(product1);

        when(productService.updateProduct(product1,301)).thenReturn(product1);
        this.mockMvc.perform(put("/product/update/301").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void testDeleteProduct() throws Exception {
       when(productService.deleteProduct(302)).thenReturn("Product removed successfully");
       this.mockMvc.perform(delete("/product/delete/302")).andDo(print()).andExpect(status().isNotFound());
    }
}