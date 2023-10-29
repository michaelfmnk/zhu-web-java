package com.example.webjavaserver.controller;

import com.example.webjavaserver.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ThymeleafStyleController {

    private ArrayList<User> users = new ArrayList<>(Arrays.asList(
            User.builder()
                    .id(0)
                    .name("John Doe 30")
                    .email("email@email.com")
                    .friends(List.of("Jane Doe", "Jack Doe"))
                    .age(15)
                    .build(),
            User.builder()
                    .id(1)
                    .name("John Doe 30")
                    .email("email@email.com")
                    .friends(List.of("Jane Doe", "Jack Doe"))
                    .age(30)
                    .build(),
            User.builder()
                    .id(2)
                    .name("John Doe 30")
                    .email("email@email.com")
                    .friends(List.of("Jane Doe", "Jack Doe"))
                    .age(30)
                    .build()
    ));

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/users/{id}")
    public ModelAndView getUserById(@PathVariable("id") int id) {
        User attributeValue = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("user", attributeValue);
        return modelAndView;
    }

    @PostMapping("/users")
    public ModelAndView submit(User user) {
        user.setId(users.size());
        users.add(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/welcome")
    public ModelAndView welcome(@RequestParam(defaultValue = "World") String name) {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        modelAndView.addObject("message", "Hello, " + name + "!");
        modelAndView.addObject("name", name);
        return modelAndView;
    }
}
