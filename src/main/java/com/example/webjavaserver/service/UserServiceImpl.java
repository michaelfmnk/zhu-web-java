package com.example.webjavaserver.service;

import com.example.webjavaserver.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {


    List<User> users = List.of(
            User.builder()
                    .name("John Doe 30")
                    .email("johndoe@gmail.com")
                    .friends(List.of("Jane Doe", "Jack Doe"))
                    .age(30)
                    .build(),
            User.builder()
                    .name("John Doe 12")
                    .email("johndoe@gmail.com")
                    .friends(List.of("Jane Doe", "Jack Doe"))
                    .age(12)
                    .build(),
            User.builder()
                    .name("John Doe 44")
                    .email("johndoe@gmail.com")
                    .friends(List.of("Jane Doe", "Jack Doe"))
                    .age(44)
                    .build(),
            User.builder()
                    .name("John Doe 12")
                    .email("johndoe@gmail.com")
                    .friends(List.of("Jane Doe", "Jack Doe"))
                    .age(12)
                    .build()
    );

    @Override
    public User createUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        users.remove(id);
    }

    @Override
    public User updateUser(
            Integer id,
            User user
    ) {
        User updatedUser = users.get(id);
        if (updatedUser == null) {
            return null;
        }

        updatedUser.name = user.name;
        updatedUser.email = user.email;
        updatedUser.friends = user.friends;
        updatedUser.age = user.age;

        return updatedUser;
    }

    @Override
    public List<User> getUsers(Integer age) {
        if (age != null) {
            return this.users.stream()
                    .filter(user -> user.age == age)
                    .toList();
        }

        return this.users;
    }

    @Override
    public User getUserById(int id) {
        if (id < 0 || id >= this.users.size()) {
            return null;
        }

        return this.users.get(id);
    }

}
