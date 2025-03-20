package com.example.ps_backend_assignment.service;

import com.example.ps_backend_assignment.DTO.UserDTO;
import com.example.ps_backend_assignment.domains.User;
import com.example.ps_backend_assignment.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class loadUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void loadUser(){
        //https://dummyjson.com/users
        UserResponse response = restTemplate.getForObject("https://dummyjson.com/users", UserResponse.class);
        List<User> users = response.getUsers().stream()
                .map(userDTO -> {
                    User user = new User();
                    user.setId(userDTO.getId());
                    user.setFirstName(userDTO.getFirstName());
                    user.setLastName(userDTO.getLastName());
                    user.setEmail(userDTO.getEmail());
                    user.setRole(userDTO.getRole());
                    user.setAge(userDTO.getAge());
                    user.setSsn(userDTO.getSsn());
                    return user;
                }).toList();
        userRepository.deleteAll();
        userRepository.saveAll(users);
    }
    private static class UserResponse {
        private List<UserDTO> users;
        public List<UserDTO> getUsers() { return users; }
        public void setUsers(List<UserDTO> users) { this.users = users; }
    }
}
