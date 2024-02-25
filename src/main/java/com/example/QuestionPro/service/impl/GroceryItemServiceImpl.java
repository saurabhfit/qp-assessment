package com.example.QuestionPro.service.impl;

import com.example.QuestionPro.model.GroceryItem;
import com.example.QuestionPro.repository.GroceryItemRepository;
import com.example.QuestionPro.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {
    @Autowired
    private GroceryItemRepository repository;

    @Override
    public GroceryItem createGroceryItem(GroceryItem groceryItem) {
        return repository.save(groceryItem);
    }

    @Override
    public Optional<GroceryItem> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<GroceryItem> findAll() {
        return repository.findAll();
    }

    @Override
    public GroceryItem updateGroceryItem(GroceryItem newGroceryItem) {
        return repository.save(newGroceryItem);
    }

    @Override
    public boolean deleteById(Long id) {
        repository.deleteById(id);
        return true;
    }
}
