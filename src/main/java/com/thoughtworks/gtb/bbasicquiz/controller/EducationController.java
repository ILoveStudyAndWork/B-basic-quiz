package com.thoughtworks.gtb.bbasicquiz.controller;

import com.thoughtworks.gtb.bbasicquiz.domain.Education;
import com.thoughtworks.gtb.bbasicquiz.exception.UserNotExistException;
import com.thoughtworks.gtb.bbasicquiz.repository.UserRepository;
import com.thoughtworks.gtb.bbasicquiz.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EducationController {

    private final EducationService educationService;
    private final UserRepository userRepository;

    public EducationController(EducationService educationService, UserRepository userRepository) {
        this.educationService = educationService;
        this.userRepository = userRepository;
    }

    @PostMapping("/users/{userId}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public Education addEducationById(@PathVariable long userId, @RequestBody  @Valid Education education) throws UserNotExistException {
        return educationService.addEducationById(userId,education);
    }

    @GetMapping("/users/{userId}/educations")
    public List<Education> addEducationById(@PathVariable long userId) throws UserNotExistException {
        return educationService.getEducationById(userId);
    }


}
