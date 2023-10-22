package com.example.webjavaserver.service;

import com.example.webjavaserver.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User updateUser(Integer id, User user);

    List<User> getUsers(Integer age);

    User getUserById(int id);

    void deleteUser(int id);
}
