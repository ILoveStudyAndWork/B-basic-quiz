package com.thoughtworks.gtb.bbasicquiz.repository;

import com.thoughtworks.gtb.bbasicquiz.domain.Education;
import com.thoughtworks.gtb.bbasicquiz.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class EducationRepositoryTest {

    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;
    private Education firstEducation;
    private Education secondEducation;
    private User user;
    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(123L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();

        firstEducation = Education.builder()
                .description("description")
                .title("title")
                .year(2012L)
                .user(user)
                .build();

        secondEducation = Education.builder()
                .description("desc")
                .title("t")
                .year(2009L)
                .user(user)
                .build();
    }

    @AfterEach
    void tearDown() {
        educationRepository.deleteAll();
        entityManager.clear();
    }

    @Test
    void should_return_educations_when_id_exists(){
        entityManager.persistAndFlush(firstEducation);
        entityManager.persistAndFlush(secondEducation);

        List<Education> educations = educationRepository.findAllByUserId(1L);
        assertThat(educations).contains(firstEducation, secondEducation);

    }

    @Test
    void should_return_empty_list_when_id_not_exists(){
        entityManager.persistAndFlush(firstEducation);
        entityManager.persistAndFlush(secondEducation);

        List<Education> educations = educationRepository.findAllByUserId(2L);
        assertThat(educations).isEqualTo(null);
    }

}
