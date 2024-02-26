package com.example.QuestionPro.service;

import com.example.QuestionPro.model.GroceryItem;
import com.example.QuestionPro.model.ValidationException;
import com.example.QuestionPro.model.payload.GroceryItemDTO;
import com.example.QuestionPro.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface GroceryItemService {

    public List<GroceryItem> createGroceryItems(List<GroceryItemDTO> groceryItemDTOs);
    public Optional<GroceryItem> findById(Long id);
    public List<GroceryItem> findAll();
    public GroceryItem updateGroceryItem(GroceryItemDTO newGroceryItem, long id) throws ValidationException;
    public boolean deleteById(Long id);

}
