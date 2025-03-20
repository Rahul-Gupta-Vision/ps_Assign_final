package com.example.ps_backend_assignment.repository;

import com.example.ps_backend_assignment.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(String role);
    List<User> findAllByOrderByAgeAsc();
    List<User> findAllByOrderByAgeDesc();
    Optional<User> findBySsn(String ssn);
}
