package com.example.webjavaserver.repository;

import com.example.webjavaserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByAge(int age);
}
