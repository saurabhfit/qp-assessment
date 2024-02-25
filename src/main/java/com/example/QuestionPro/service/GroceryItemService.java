package com.example.QuestionPro.service;

import com.example.QuestionPro.model.GroceryItem;
import com.example.QuestionPro.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface GroceryItemService {

    public GroceryItem createGroceryItem(GroceryItem groceryItem);
    public Optional<GroceryItem> findById(Long id);
    public List<GroceryItem> findAll();
    public GroceryItem updateGroceryItem(GroceryItem newGroceryItem);
    public boolean deleteById(Long id);

}
