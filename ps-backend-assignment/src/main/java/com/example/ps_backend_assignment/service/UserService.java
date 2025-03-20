package com.example.ps_backend_assignment.service;

import com.example.ps_backend_assignment.domains.User;
import com.example.ps_backend_assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public List<User> getUsersSortedByAge(String order) {
        return "desc".equalsIgnoreCase(order) ?
                userRepository.findAllByOrderByAgeDesc() :
                userRepository.findAllByOrderByAgeAsc();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserBySsn(String ssn) {
        return userRepository.findBySsn(ssn);
    }
}
