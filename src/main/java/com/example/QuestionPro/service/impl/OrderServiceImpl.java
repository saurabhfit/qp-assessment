package com.example.QuestionPro.service.impl;

import com.example.QuestionPro.exception.ValidationException;
import com.example.QuestionPro.model.GroceryItem;
import com.example.QuestionPro.model.Order;
import com.example.QuestionPro.model.User;
import com.example.QuestionPro.repository.OrderRepository;
import com.example.QuestionPro.service.GroceryItemService;
import com.example.QuestionPro.service.OrderService;
import com.example.QuestionPro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserService userService;
    @Autowired
    private GroceryItemService groceryItemService;
    @Autowired
    private OrderRepository orderRepository;
    public Order createOrder(Long userId, List<Long> itemIds) throws ValidationException {
        Optional<User> user = userService.findUserById(userId);
        allGroceryItemsArePresent(itemIds);
        List<GroceryItem> groceryItems = itemIds.stream()
                .map(itemId->groceryItemService.findById(itemId).get())
                .collect(Collectors.toList());

        Order order = new Order();
        order.setUser(user.get());
        order.setGroceryItems(groceryItems);
        return orderRepository.save(order);
    }

    private void allGroceryItemsArePresent(List<Long> itemIds) throws ValidationException {
        for(Long itemId: itemIds){
            if(!groceryItemService.findById(itemId).isPresent())
                throw new ValidationException("Grocery item with id "+itemId+" not present for booking");
        }
    }

}
