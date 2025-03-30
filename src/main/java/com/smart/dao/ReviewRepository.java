package com.smart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findTop5ByOrderByIdDesc(); // Fetch latest 5 reviews


}
