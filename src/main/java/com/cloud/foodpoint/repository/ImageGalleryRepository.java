package com.cloud.foodpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.foodpoint.modal.ImageGallery;



@Repository
public interface ImageGalleryRepository extends JpaRepository<ImageGallery, Long>{

}