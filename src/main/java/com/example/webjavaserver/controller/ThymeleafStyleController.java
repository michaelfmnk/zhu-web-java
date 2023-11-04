package com.example.webjavaserver.controller;

import com.example.webjavaserver.model.User;
import com.example.webjavaserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ThymeleafStyleController {
    private final UserService userService;

    @GetMapping("/users/{id}")
    public ModelAndView getUserById(@PathVariable("id") int id) {
        User attributeValue = userService.getUserById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("user", attributeValue);
        return modelAndView;
    }

    @PostMapping("/users")
    public ModelAndView submit(User user) {
        userService.createUser(user);
        var users = userService.getUsers();

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
