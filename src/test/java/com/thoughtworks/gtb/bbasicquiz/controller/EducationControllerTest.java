package com.thoughtworks.gtb.bbasicquiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionConstants;
import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionFromConstants;
import com.thoughtworks.gtb.bbasicquiz.domain.Education;
import com.thoughtworks.gtb.bbasicquiz.domain.User;
import com.thoughtworks.gtb.bbasicquiz.exception.UserNotExistException;
import com.thoughtworks.gtb.bbasicquiz.service.EducationService;
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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.MapperFeature.USE_ANNOTATIONS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EducationController.class)
@AutoConfigureJsonTesters
class EducationControllerTest {


    @MockBean
    EducationService educationService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<Education> educationJson;

    private User user;
    private Education education;
    @BeforeEach
    void setUp() {
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
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(educationService);
    }

    @Nested
    class addEducationById{

        @Nested
        class WhenUserIdExistAndMassageValid{

            @Test
            void should_return_education() throws Exception {
                Education educationReturn = Education.builder()
                        .id(1L)
                        .description("description")
                        .title("title")
                        .year(2012L)
                        .userId(123L)
                        .build();
                when(educationService.addEducationById(123L,education)).thenReturn(educationReturn);

                mockMvc.perform(post("/users/123/educations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(educationJson.write(education).getJson()))
                        .andExpect(status().isCreated());
                verify(educationService).addEducationById(123L,education);
            }
        }

        @Nested
        class WhenUserIdNotExist{

            @Test
            void should_return_user_not_exist() throws Exception {
                when(educationService.addEducationById(234L,education)).thenThrow(new UserNotExistException(ExceptionFromConstants.EDUCATION_INFO, 234L));

                mockMvc.perform(post("/users/234/educations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(educationJson.write(education).getJson()))
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.NOT_FOUND_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.NOT_FOUND_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.USER_NOT_EXIST_WHEN_GET_EDUCATION+234L)));

                verify(educationService).addEducationById(234L,education);
            }
        }

        @Nested
        class WhenEducationMessageNotValid{

            @Test
            void should_return_YEAR_CAN_NO_BE_NULL() throws Exception {
                education.setYear(null);
                mockMvc.perform(post("/users/123/educations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(educationJson.write(education).getJson()))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.YEAR_CAN_NO_BE_NULL)));
            }

            @Test
            void should_return_EDUCATION_TITLE_CAN_NOT_BE_NULL() throws Exception {
                education.setTitle(null);
                mockMvc.perform(post("/users/123/educations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(educationJson.write(education).getJson()))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.EDUCATION_TITLE_CAN_NOT_BE_NULL)));
            }
        }

        @Test
        void should_return_EDUCATION_TITLE_LENGTH_CONSTRAIN() throws Exception {
            education.setTitle("");
            mockMvc.perform(post("/users/123/educations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(educationJson.write(education).getJson()))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                    .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                    .andExpect(jsonPath("$.message", is(ExceptionConstants.EDUCATION_TITLE_LENGTH_CONSTRAIN)));
        }


        @Test
        void should_return_EDUCATION_DESCRIPTION_CAN_NOT_BE_NULL() throws Exception {
            education.setDescription(null);
            mockMvc.perform(post("/users/123/educations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(educationJson.write(education).getJson()))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                    .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                    .andExpect(jsonPath("$.message", is(ExceptionConstants.EDUCATION_DESCRIPTION_CAN_NOT_BE_NULL)));
        }

        @Test
        void should_return_EDUCATION_DESCRIPTION_LENGTH_CONSTRAIN() throws Exception {
            education.setDescription("");
            mockMvc.perform(post("/users/123/educations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(educationJson.write(education).getJson()))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.error",is(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)))
                    .andExpect(jsonPath("$.status",is(ExceptionConstants.BAD_REQUEST_STATUS)))
                    .andExpect(jsonPath("$.message", is(ExceptionConstants.EDUCATION_DESCRIPTION_LENGTH_CONSTRAIN)));
        }
    }

    @Nested
    class getEducationById{

        @Nested
        class whenUserIdExist{

            @Test
            void should_return_educations() throws Exception {
                List<Education> educations = new ArrayList<>();
                educations.add(education);
                when(educationService.getEducationById(123L)).thenReturn(educations);

                mockMvc.perform(get("/users/{userId}/educations", 123))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$",hasSize(1)));

                verify(educationService).getEducationById(123L);
            }
        }

        @Nested
        class whenUserIdNotExist{
            @Test
            void should_return_user_not_exist() throws Exception {
                when(educationService.getEducationById(234L)).thenThrow(new UserNotExistException(ExceptionFromConstants.EDUCATION_INFO, 234L));

                mockMvc.perform(get("/users/234/educations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(educationJson.write(education).getJson()))
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.error",is(ExceptionConstants.NOT_FOUND_MESSAGE)))
                        .andExpect(jsonPath("$.status",is(ExceptionConstants.NOT_FOUND_STATUS)))
                        .andExpect(jsonPath("$.message", is(ExceptionConstants.USER_NOT_EXIST_WHEN_GET_EDUCATION+234L)));

                verify(educationService).getEducationById(234L);
            }
        }
    }

}


