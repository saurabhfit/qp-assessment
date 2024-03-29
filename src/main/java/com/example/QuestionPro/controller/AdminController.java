package com.example.QuestionPro.controller;

import com.example.QuestionPro.model.GroceryItem;
import com.example.QuestionPro.exception.ValidationException;
import com.example.QuestionPro.model.payload.GroceryItemDTO;
import com.example.QuestionPro.model.payload.ResponsePayload;
import com.example.QuestionPro.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {

    @Autowired
    private GroceryItemService groceryItemService;

    @PostMapping("/addGroceryItems")
    public ResponseEntity<ResponsePayload> addGroceryItems(@RequestBody List<GroceryItemDTO> groceryItems){
        ResponseEntity<ResponsePayload> response = new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        List<GroceryItem> savedGroceryItems = groceryItemService.createGroceryItems(groceryItems);
        if(savedGroceryItems.isEmpty()){
            ResponsePayload errorPayload = new ResponsePayload();
            errorPayload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
            errorPayload.setResponseMessage("Failed saving grocery items to db");
            response = new ResponseEntity<>(errorPayload, HttpStatus.EXPECTATION_FAILED);
        }else{
            ResponsePayload responsePayload = new ResponsePayload();
            responsePayload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.SUCCESS);
            responsePayload.setResponseMessage("Grocery Items saved successfully");
            responsePayload.addResponseDetails(savedGroceryItems);
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

    @DeleteMapping("removeGroceryItem/{id}")
    public ResponseEntity<ResponsePayload> deleteGroceryItemById(@PathVariable long id){
        ResponseEntity<ResponsePayload> response = null;
        ResponsePayload payload = null;
        if(groceryItemService.deleteById(id)){
            payload = new ResponsePayload();
            payload.setResponseMessage("grocery item id "+id+" removed from system");
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.SUCCESS);
            response = new ResponseEntity<>(payload, HttpStatus.OK);
        }else{
            payload = new ResponsePayload();
            payload.setResponseMessage("grocery item id "+id+" unable to remove from system");
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
            response = new ResponseEntity<>(payload, HttpStatus.EXPECTATION_FAILED);
        }
        return response;
    }

    @PatchMapping("updateGroceryItem/{id}")
    public ResponseEntity<ResponsePayload> updateGroceryItem(@RequestBody GroceryItemDTO groceryItemDTO,
                                                             @PathVariable long id) throws ValidationException {
        ResponseEntity<ResponsePayload> response = null;
        ResponsePayload payload = null;

        GroceryItem groceryItem = groceryItemService.updateGroceryItem(groceryItemDTO, id);
        payload = new ResponsePayload();
        payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.SUCCESS);
        payload.setResponseMessage("Details updated of grocery item with id "+id);
        payload.addResponseDetails(groceryItem);

        response = new ResponseEntity<>(payload, HttpStatus.ACCEPTED);
        return response;
    }
}
