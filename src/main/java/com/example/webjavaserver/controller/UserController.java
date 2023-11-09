package com.example.webjavaserver.controller;

import com.example.webjavaserver.dto.UserDto;
import com.example.webjavaserver.model.User;
import com.example.webjavaserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;


    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable("id") int id,
            Authentication authentication
    ) {
        var user = userService.getUserById(id);
        var principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        // authorization
        if (!Objects.equals(principal.getUsername(), user.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserDto.of(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(value = "age", required = false) Integer age
    ) {
        return ResponseEntity.ok(userService.getUsers(age).stream()
                .map(UserDto::of)
                .toList()
        );
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") int id,
            @RequestBody User request) {
        var user = userService.updateUser(id, request);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable("id") int id
    ) {
        userService.deleteUser(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User request) {
        var createdUser = userService.createUser(request);
        return ResponseEntity.ok(createdUser);
    }

}
