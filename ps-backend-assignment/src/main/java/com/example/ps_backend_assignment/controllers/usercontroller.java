package com.example.ps_backend_assignment.controllers;

import com.example.ps_backend_assignment.domains.User;
import com.example.ps_backend_assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class usercontroller {

    @Autowired
    private UserService userService;

    @GetMapping("/load")
    public String loadUsers(){
        return "Loading users";
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return userService.getUsersByRole(role);
    }

    @GetMapping("/sorted")
    public List<User> getUsersSortedByAge(@RequestParam(defaultValue = "asc") String order) {
        return userService.getUsersSortedByAge(order);
    }

    @GetMapping("/find")
    public ResponseEntity<User> findUser(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String ssn) {
        if (id != null) {
            return userService.getUserById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } else if (ssn != null) {
            return userService.getUserBySsn(ssn)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.badRequest().build();
    }
}
