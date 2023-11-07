package com.example.project_one;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@ControllerAdvice
public class UserController {
    private final List<UserResponse> userList = new ArrayList<>();

    public UserController() {
        userList.add(new UserResponse("Dima Korsh", "dimakrsh@gmail.com", List.of("Rm Sh", "Kr Sp")));
        userList.add(new UserResponse("Ivan Vovchok", "vovk@ukr.net", List.of("Al Ro", "Sv Se")));
        userList.add(new UserResponse("Petya Bamper", "bamper@gmail.com", List.of("Sus", "Petro Bamper (fake)")));
    }

    // NICE!
    private final AtomicLong idCounter = new AtomicLong();

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        UserResponse user = findUserById(id);
        if (user != null) {
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping("/user")
    public UserResponse getUser() {
        return userList.get(0);
    }

    @GetMapping("/me")
    public String getMe() {
        return "Hello from the user controller";
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUserById(@PathVariable Long id, @RequestBody UserResponse updatedUser) {
        UserResponse existingUser = findUserById(id);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setFreands(updatedUser.getFreands());
            return existingUser;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @DeleteMapping("/users/{id}")
    public UserResponse deleteUserById(@PathVariable Long id) {
        UserResponse existingUser = findUserById(id);
        if (existingUser != null) {
            userList.remove(existingUser);
            return existingUser;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody UserResponse newUser) {
        newUser.setId(idCounter.getAndIncrement());
        userList.add(newUser);
        return newUser;
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        // you're adding the same users every time you call this method
        // you should only add them once
        // I moved to constructor
//        userList.add(new UserResponse("Dima Korsh", "dimakrsh@gmail.com", List.of("Rm Sh", "Kr Sp")));
//        userList.add(new UserResponse("Ivan Vovchok", "vovk@ukr.net", List.of("Al Ro", "Sv Se")));
//        userList.add(new UserResponse("Petya Bamper", "bamper@gmail.com", List.of("Sus", "Petro Bamper (fake)")));
        return userList;
    }

    private UserResponse findUserById(Long id) {
        for (UserResponse user : userList) {
            if (user.getId() != null && user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public void handleResponseStatusException(ResponseStatusException ex) {

    }
}









































