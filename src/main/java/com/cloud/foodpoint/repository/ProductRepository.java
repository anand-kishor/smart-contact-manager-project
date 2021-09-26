package com.cloud.foodpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.foodpoint.modal.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
