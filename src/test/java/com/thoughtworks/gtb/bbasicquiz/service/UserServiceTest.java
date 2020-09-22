package com.thoughtworks.gtb.bbasicquiz.service;

import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionConstants;
import com.thoughtworks.gtb.bbasicquiz.domain.User;
import com.thoughtworks.gtb.bbasicquiz.exception.UserNotExistException;
import com.thoughtworks.gtb.bbasicquiz.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;
    @Mock
    private UserRepository userRepository;
    private User user;
    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        user = User.builder()
                .id(123L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();
    }

    @Nested
    class getUserById{

        @Nested
        class WhenIdExist{

            @Test
            void should_return_user() throws UserNotExistException {
                when(userRepository.findById(123L)).thenReturn(Optional.of(user));
                User foundUser = userService.getUserById(123L);

                assertThat(foundUser).isEqualTo(User.builder()
                        .id(123L)
                        .name("Panda")
                        .age(24L)
                        .avatar("http://...")
                        .description("A good guy.")
                        .build());

            }
        }

        @Nested
        class WhenIdNotExist{

            @Test
            void should_return_user() {
                when(userRepository.findById(222L)).thenReturn(Optional.empty());
                UserNotExistException thrownException = assertThrows(UserNotExistException.class, () -> {
                    userService.getUserById(222L);
                });

                assertThat(thrownException.getMessage()).containsSequence(ExceptionConstants.USER_NOT_EXIST_WHEN_GET_USER_INFO+222L);

            }
        }

    }

    @Nested
    class register{

        @Test
        void should_return_user() {
            when(userRepository.save(user)).thenReturn(user);

            User registeredUser = userService.register(user);

            assertThat(registeredUser).isEqualTo(User.builder()
                    .id(123L)
                    .name("Panda")
                    .age(24L)
                    .avatar("http://...")
                    .description("A good guy.")
                    .build());

        }
    }
}
