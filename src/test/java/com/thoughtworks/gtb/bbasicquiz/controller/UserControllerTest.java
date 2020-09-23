package com.thoughtworks.gtb.bbasicquiz.controller;

import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionConstants;
import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionFromConstants;
import com.thoughtworks.gtb.bbasicquiz.domain.User;
import com.thoughtworks.gtb.bbasicquiz.exception.UserNotExistException;
import com.thoughtworks.gtb.bbasicquiz.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<User> userJson;

    private User firstUser;
    @BeforeEach
    void setUp() {
        firstUser = User.builder()
                .id(123L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(userService);
    }

    @Nested
    class GetUserById {

        @Nested
        class WhenUserIdExists {

            @Test
            public void should_return_user_by_id() throws Exception {
                when(userService.getUserById(123L)).thenReturn(firstUser);

                MockHttpServletResponse response = mockMvc.perform(get("/users/{id}", 123))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();

                assertThat(response.getContentAsString()).isEqualTo(
                        userJson.write(firstUser).getJson());

                verify(userService).getUserById(123L);
            }
        }

        @Nested
        class WhenUserIdNotExisted {

            @Test
            public void should_return_user_not_found_exception() throws Exception {
                when(userService.getUserById(123L)).thenThrow(new UserNotExistException(ExceptionFromConstants.BASIC_INFO,123L));

                mockMvc.perform(get("/users/{id}", 123L))
                        .andExpect(status().isNotFound())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.NOT_FOUND_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.NOT_FOUND_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.USER_NOT_EXIST_WHEN_GET_USER_INFO+123L)));

                verify(userService).getUserById(123L);
            }
        }
    }

    @Nested
    class register{
        @Nested
        class whenUserMessagePassValidation{

            @Test
            public void should_return_user() throws Exception {
                when(userService.register(firstUser)).thenReturn(firstUser);

                MockHttpServletResponse response = mockMvc.perform(post("/users")
                        .content(userJson.write(firstUser).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

                assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
                verify(userService).register(firstUser);
            }
        }

        @Nested
        class whenUserMessageNotPassValidation{

            @Test
            public void should_return_name_can_not_be_null_exception() throws Exception {
                firstUser.setName(null);
                mockMvc.perform(post("/users")
                        .content(userJson.write(firstUser).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.NAME_CAN_NO_BE_NULL)));
            }

            @Test
            public void should_return_name_length_constrain_exception() throws Exception {
                firstUser.setName("");
                mockMvc.perform(post("/users")
                        .content(userJson.write(firstUser).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.NAME_LENGTH_CONSTRAIN)));
            }

            @Test
            public void should_return_age_can_not_be_null_exception() throws Exception {
                firstUser.setAge(null);
                mockMvc.perform(post("/users")
                        .content(userJson.write(firstUser).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.AGE_CAN_NOT_BE_NULL)));
            }

            @Test
            public void should_return_age_length_constrain_exception() throws Exception {
                firstUser.setAge(15L);
                mockMvc.perform(post("/users")
                        .content(userJson.write(firstUser).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.AGE_LENGTH_CONSTRAIN)));
            }

            @Test
            public void should_return_avatar_can_not_be_null_exception() throws Exception {
                firstUser.setAvatar(null);
                mockMvc.perform(post("/users")
                        .content(userJson.write(firstUser).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.AVATAR_CAN_NOT_BE_NULL)));
            }

            @Test
            public void should_return_avatar_length_constrain_exception() throws Exception {
                firstUser.setAvatar("");
                mockMvc.perform(post("/users")
                        .content(userJson.write(firstUser).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.AVATAR_LENGTH_CONSTRAIN)));
            }

            @Test
            public void should_return_user_description_length_constrain_exception() throws Exception {
                final int MinNotValidLength = 1025;
                firstUser.setDescription(prePareNotValidDescription(MinNotValidLength));
                mockMvc.perform(post("/users")
                        .content(userJson.write(firstUser).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.USER_DESCRIPTION_LENGTH_CONSTRAIN)));
            }

            private String prePareNotValidDescription(int stringLength) {
                byte[] b = new byte[stringLength];
                Arrays.fill(b, (byte)0x41);
                return new String(b);
            }

        }
    }

}
