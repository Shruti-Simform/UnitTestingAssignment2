package com.database.mysql.productManagement.controller;

import com.database.mysql.productManagement.customHandler.ProductNotFound;
import com.database.mysql.productManagement.entity.Product;
import com.database.mysql.productManagement.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ProductService service;

    @PostMapping("/create")
    public Product saveProduct(@RequestBody Product product) {
        logger.info("Product created");
        return service.createProduct(product);
    }

    @GetMapping("/get")
    public List<Product> getProducts() {
        logger.info("Products displayed");
        return service.getAllProducts();
    }

    @GetMapping("/get/{id}")
    public Object getById(@PathVariable int id) {
        try {
            if (service.productExistsById(id)) {
                logger.info("Product Id {} exists", id);
                return service.getById(id);
            }
            throw new ProductNotFound();
        } catch (ProductNotFound e) {
            logger.error("Product Id {} doesn't exist", id);
        }
        return "Product doesn't exist";
    }

    @PutMapping("/update/{id}")
    public Object updateProduct(@RequestBody Product product, @PathVariable int id) {
        try {
            if (service.productExistsById(id)) {
                logger.info("Product Id {} updated", id);
                return service.updateProduct(product, id);
            }
            throw new ProductNotFound();
        } catch (ProductNotFound e) {
            logger.error("Product doesn't exist");
        }
        return "Product doesn't exist";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        try {
            if (service.productExistsById(id)) {
                service.deleteProduct(id);
                logger.warn("\nProduct Id {} deleted", id);
                return new ResponseEntity<String>("Product removed successfully", HttpStatus.OK);
            }
            throw new ProductNotFound();
        } catch (ProductNotFound e) {
            logger.error("\nProduct doesn't exist");
        }
        return new ResponseEntity<>("Product doesn't exist", HttpStatus.NOT_FOUND);
    }
}
