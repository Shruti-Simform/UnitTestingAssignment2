package com.database.mysql.productManagement.repository;

import com.database.mysql.productManagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductManagementRepository extends JpaRepository<Product,Integer> {
}
