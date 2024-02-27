package com.example.QuestionPro.service;

import com.example.QuestionPro.exception.ValidationException;
import com.example.QuestionPro.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long userId, List<Long> itemIds) throws ValidationException;
}
