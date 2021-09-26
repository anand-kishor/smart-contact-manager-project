package com.cloud.foodpoint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.foodpoint.modal.ImageGallery;
import com.cloud.foodpoint.modal.Product;
import com.cloud.foodpoint.repository.ProductRepository;



@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository ;
	
	public void saveProduct(Product product) {
		productRepository.save(product);	
	}

	public List<Product> getAllActiveImages() {
		return productRepository.findAll();
	}

	public Optional<Product> getImageById(Integer id) {
		return productRepository.findById(id);
	}

	/*
	 * public List<Product> getAllActiveImages() { // TODO Auto-generated method
	 * stub return productRepository.findAll(); }
	 */
	

}
