package com.example.QuestionPro.controller;

import com.example.QuestionPro.exception.ValidationException;
import com.example.QuestionPro.model.GroceryItem;
import com.example.QuestionPro.model.Order;
import com.example.QuestionPro.model.User;
import com.example.QuestionPro.model.UserType;
import com.example.QuestionPro.model.payload.BookingItemsDTO;
import com.example.QuestionPro.model.payload.ResponsePayload;
import com.example.QuestionPro.model.payload.UserDTO;
import com.example.QuestionPro.service.GroceryItemService;
import com.example.QuestionPro.service.OrderService;
import com.example.QuestionPro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private GroceryItemService groceryItemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload> getUserDetailsById(@PathVariable Long id){
        ResponsePayload payload;
        ResponseEntity<ResponsePayload> response;
        Optional<User> optionalUser = userService.findUserById(id);
        if(optionalUser.isPresent()){
            payload = new ResponsePayload();
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.SUCCESS);
            payload.setResponseMessage("User found with id "+id);
            payload.addResponseDetails(optionalUser.get());
            response = new ResponseEntity<>(payload, HttpStatus.OK);
        }else{
            payload = new ResponsePayload();
            payload.setResponseMessage("User with id "+id+" not found");
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
            response = new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping()
    public ResponseEntity<ResponsePayload> getAllUserDetails(){
        ResponsePayload payload;
        ResponseEntity<ResponsePayload> response;
        List<User> userList = userService.findAll();
        if(!userList.isEmpty()){
            payload = new ResponsePayload();
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.SUCCESS);
            payload.setResponseMessage("Total "+userList.size()+" users found");
            payload.addResponseDetails(userList);
            response = new ResponseEntity<>(payload, HttpStatus.OK);
        }else{
            payload = new ResponsePayload();
            payload.setResponseMessage("No User found");
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
            response = new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<ResponsePayload> createUser(@RequestBody UserDTO userDTO){
        ResponseEntity<ResponsePayload> response = new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        User user = userService.createUser(userDTO);
        if(user==null){
            ResponsePayload errorPayload = new ResponsePayload();
            errorPayload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
            errorPayload.setResponseMessage("User creation failed");
            response = new ResponseEntity<>(errorPayload, HttpStatus.EXPECTATION_FAILED);
        }else{
            ResponsePayload responsePayload = new ResponsePayload();
            responsePayload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.SUCCESS);
            responsePayload.setResponseMessage("User creation successful");
            responsePayload.addResponseDetails(user);
            response = new ResponseEntity<>(responsePayload, HttpStatus.CREATED);
        }
        return response;
    }

    @GetMapping("/viewAllGroceryItems")
    public ResponseEntity<ResponsePayload> showAllGroceryItems(){
        ResponseEntity<ResponsePayload> response = null;
        List<GroceryItem> groceryItems = groceryItemService.findAll();
        if(groceryItems.isEmpty()){
            ResponsePayload payload = new ResponsePayload();
            payload.setResponseMessage("Not found");
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
            payload.setResponseDetails(new ArrayList<>());
            response = new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }else{
            ResponsePayload payload = new ResponsePayload();
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.SUCCESS);
            payload.addResponseDetails(groceryItems);
            payload.setResponseMessage(groceryItems.size()+" items found");
            response = new ResponseEntity<>(payload, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/bookItems/{userId}")
    public ResponseEntity<ResponsePayload> bookItems(@RequestBody BookingItemsDTO bookingItemsDTO,
                                                     @PathVariable Long userId) throws ValidationException {
        if(bookingItemsDTO==null || bookingItemsDTO.getItemIds()==null || bookingItemsDTO.getItemIds().isEmpty()){
            ResponsePayload payload = new ResponsePayload();
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
            payload.setResponseMessage("Please select items first");
            ResponseEntity<ResponsePayload> response = new ResponseEntity<>(payload, HttpStatus.EXPECTATION_FAILED);
            return response;
        }
        if(userId==null || !userService.findUserById(userId).isPresent()){
            ResponsePayload payload = new ResponsePayload();
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
            payload.setResponseMessage("User does not exist");
            ResponseEntity<ResponsePayload> response = new ResponseEntity<>(payload, HttpStatus.EXPECTATION_FAILED);
            return response;
        }
        if(!userService.findUserById(userId).get().getUserType().equals(UserType.USER)){
            ResponsePayload payload = new ResponsePayload();
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
            payload.setResponseMessage("Admin users not allowed to book items");
            ResponseEntity<ResponsePayload> response = new ResponseEntity<>(payload, HttpStatus.EXPECTATION_FAILED);
            return response;
        }

        Order order = orderService.createOrder(userId, bookingItemsDTO.getItemIds());

        ResponsePayload payload = new ResponsePayload();
        payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.SUCCESS);
        payload.setResponseMessage("Order created: "+order.getId());
        payload.addResponseDetails(order);
        ResponseEntity<ResponsePayload> response = new ResponseEntity<>(payload, HttpStatus.CREATED);
        return response;
    }
}
