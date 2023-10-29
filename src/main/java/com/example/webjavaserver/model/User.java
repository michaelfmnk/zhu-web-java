package com.example.webjavaserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    public String name;
    public String email;
    public List<String> friends;
    public int age;
}
