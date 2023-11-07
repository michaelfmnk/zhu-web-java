package com.example.project_one;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private List<String> freands;

    private static final AtomicLong idCounter = new AtomicLong(1);

    public UserResponse(String name, String email, List<String> freands) {
        this.id = idCounter.getAndIncrement();
        this.name = name;
        this.email = email;
        this.freands = freands;
    }

    public UserResponse() {
        // Пустой конструктор
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFreands() {
        return freands;
    }

    public void setFreands(List<String> freands) {
        this.freands = freands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", freands=" + freands +
                '}';
    }
}























