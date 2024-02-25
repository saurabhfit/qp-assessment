package com.example.QuestionPro.repository;

import com.example.QuestionPro.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {

}
