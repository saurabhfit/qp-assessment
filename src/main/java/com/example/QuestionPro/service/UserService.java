package com.example.QuestionPro.service;

import com.example.QuestionPro.model.User;
import com.example.QuestionPro.model.payload.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long id);
    List<User> findAll();
    User createUser(UserDTO userDTO);
    boolean deleteUser(Long id);
}
