package com.thoughtworks.gtb.bbasicquiz.service;

import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionConstants;
import com.thoughtworks.gtb.bbasicquiz.domain.Education;
import com.thoughtworks.gtb.bbasicquiz.domain.User;
import com.thoughtworks.gtb.bbasicquiz.exception.UserNotExistException;
import com.thoughtworks.gtb.bbasicquiz.repository.EducationRepository;
import com.thoughtworks.gtb.bbasicquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EducationServiceTest {

    private EducationService educationService;
    @Mock
    EducationRepository educationRepository;
    @Mock
    UserRepository userRepository;
    private Education education;
    private Education educationBeforeSave;
    private Education educationReturn;
    private User user;
    @BeforeEach
    void setUp() {
        educationService = new EducationService(userRepository,educationRepository);
        user = User.builder()
                .id(123L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();

        education = Education.builder()
                .description("description")
                .title("title")
                .year(2012L)
                .build();

        educationBeforeSave = Education.builder()
                .description("description")
                .title("title")
                .year(2012L)
                .user(user)
                .build();

        educationReturn = Education.builder()
                .id(1L)
                .description("description")
                .title("title")
                .year(2012L)
                .user(user)
                .build();
    }


    @Nested
    class addEducationById{

        @Nested
        class WhenUserIdExist{

            @Test
            void should_return_education() throws UserNotExistException {
                when(userRepository.findById(123L)).thenReturn(Optional.of(user));
                when(educationRepository.save(educationBeforeSave)).thenReturn(educationReturn);
                Education educationToBeAdd = educationService.addEducationById(123L,education);

                assertThat(educationToBeAdd).isEqualTo(educationReturn);
            }
        }

        @Nested
        class WhenUserIdNotExist{

            @Test
            void should_throw_user_not_exist_error(){
                when(userRepository.findById(234L)).thenReturn(Optional.empty());
                UserNotExistException userNotExistException = assertThrows(UserNotExistException.class, () ->{
                    educationService.addEducationById(234L,education);
                });
                assertThat(userNotExistException.getMessage()).isEqualTo(ExceptionConstants.USER_NOT_EXIST_WHEN_GET_EDUCATION+234L);
               }
        }
    }

    @Nested
    class getEducationById{

        @Nested
        class WhenUserIdExist{

            @Test
            void should_return_educations() throws UserNotExistException {
                when(userRepository.findById(123L)).thenReturn(Optional.of(user));
                when(educationRepository.findAllByUserId(123L)).thenReturn(Collections.singletonList(educationReturn));
                List<Education> educations = educationService.getEducationById(123L);

                assertThat(educations).containsOnly(educationReturn);
            }
        }

        @Nested
        class WhenUserIdNotExist{

            @Test
            void should_throw_user_not_exist_exception(){
                when(userRepository.findById(234L)).thenReturn(Optional.empty());
                UserNotExistException userNotExistException = assertThrows(UserNotExistException.class, () -> {
                    educationService.getEducationById(234L);
                });
                assertThat(userNotExistException.getMessage()).isEqualTo(ExceptionConstants.USER_NOT_EXIST_WHEN_GET_EDUCATION+234L);
            }
        }
    }

    @Nested
    class checkUserExist{

        @Nested
        class WhenUserExist{

            @Test
            void should_do_nothing() throws UserNotExistException {
                when(userRepository.findById(123L)).thenReturn(Optional.of(user));
                educationService.checkUserExist(123L);
                verify(userRepository,times(1)).findById(123L);
            }
        }
    }
}
