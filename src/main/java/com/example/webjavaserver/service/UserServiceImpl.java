package com.example.webjavaserver.service;

import com.example.webjavaserver.model.User;
import com.example.webjavaserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(
            Integer id,
            User user
    ) {
        User updatedUser = userRepository.findById(id).orElse(null);
        if (updatedUser == null) {
            return null;
        }

        updatedUser.name = user.name;
        updatedUser.email = user.email;
        updatedUser.friends = user.friends;
        updatedUser.age = user.age;

        return userRepository.save(updatedUser);
    }

    @Override
    public List<User> getUsers(Integer age) {
        if (age != null) {
            return userRepository.findByAge(age);
        }

        return userRepository.findAll();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

}
