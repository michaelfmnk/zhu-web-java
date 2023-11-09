package com.example.webjavaserver.service;

import com.example.webjavaserver.model.User;
import com.example.webjavaserver.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

class UserServiceImplTest {

    private UserService userService;

    private UserRepository userRepositoryMock;

    @BeforeEach
    public void beforeEach() {
        userRepositoryMock = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepositoryMock);
    }

    @Test
    public void shouldDelete() {
        userService.deleteUser(5);

        Mockito.verify(userRepositoryMock).deleteById(5);
    }

    @Test
    public void shouldUpdate() {
        // given
        User userInDb = User.builder()
                .id(5)
                .name("name")
                .email("email")
                .age(10)
                .build();

        Mockito.when(userRepositoryMock.findById(5))
                .thenReturn(Optional.of(userInDb));


        User newUserValues = User.builder()
                .id(5)
                .name("updatedName")
                .email("updatedEmail")
                .age(99)
                .build();

        // when
        userService.updateUser(5, newUserValues);

        // then
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepositoryMock).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        Assertions.assertEquals(5, savedUser.getId());
        Assertions.assertEquals("updatedName", savedUser.getName());
        Assertions.assertEquals("updatedEmail", savedUser.getEmail());
        Assertions.assertEquals(99, savedUser.getAge());
    }

}