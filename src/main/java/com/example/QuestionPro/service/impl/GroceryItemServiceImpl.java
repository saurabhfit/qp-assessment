package com.example.QuestionPro.service.impl;

import com.example.QuestionPro.model.GroceryItem;
import com.example.QuestionPro.exception.ValidationException;
import com.example.QuestionPro.model.payload.GroceryItemDTO;
import com.example.QuestionPro.repository.GroceryItemRepository;
import com.example.QuestionPro.service.GroceryItemService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {
    @Autowired
    private GroceryItemRepository repository;

    @Override
    public List<GroceryItem> createGroceryItems(List<GroceryItemDTO> groceryItemDTOs) {
        List<GroceryItem> savedGroceryItems = new ArrayList<>();
        groceryItemDTOs.stream().forEach(groceryItemDTO -> {
            GroceryItem groceryItem = new GroceryItem();
            groceryItem.setName(groceryItemDTO.getName());
            groceryItem.setPrice(groceryItemDTO.getPrice());
            groceryItem.setInventoryLevel(groceryItemDTO.getInventoryLevel());
            savedGroceryItems.add(repository.save(groceryItem));
        });

        return savedGroceryItems;
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
    public GroceryItem updateGroceryItem(GroceryItemDTO newGroceryItem, long id) throws ValidationException {
        Optional<GroceryItem> optionalGroceryItem = repository.findById(id);
        if(optionalGroceryItem.isPresent()){
            GroceryItem groceryItemFromDb = optionalGroceryItem.get();
            if(StringUtils.isNotEmpty(newGroceryItem.getName())){
                groceryItemFromDb.setName(newGroceryItem.getName());
            }
            if(newGroceryItem.getPrice()!=null){
                groceryItemFromDb.setPrice(newGroceryItem.getPrice());
            }
            if(newGroceryItem.getInventoryLevel()!=null){
                groceryItemFromDb.setInventoryLevel(newGroceryItem.getInventoryLevel());
            }
            GroceryItem updatedItem = repository.save(groceryItemFromDb);
            return updatedItem;
        }else{
            throw new ValidationException("Grocery item id "+id+" not present in inventory");
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
