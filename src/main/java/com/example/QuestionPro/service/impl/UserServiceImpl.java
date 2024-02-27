package com.example.QuestionPro.service.impl;

import com.example.QuestionPro.model.User;
import com.example.QuestionPro.model.payload.UserDTO;
import com.example.QuestionPro.repository.UserRepository;
import com.example.QuestionPro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUserType(userDTO.getUserType());
        user.setAddress(userDTO.getAddress());
        user = userRepository.save(user);
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
