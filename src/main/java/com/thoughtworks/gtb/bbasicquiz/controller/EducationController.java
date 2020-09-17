package com.thoughtworks.gtb.bbasicquiz.controller;

import com.thoughtworks.gtb.bbasicquiz.domain.Education;
import com.thoughtworks.gtb.bbasicquiz.exception.UserNotExistException;
import com.thoughtworks.gtb.bbasicquiz.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/users/{userId}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public Education addEducationById(@PathVariable long userId, @RequestBody  @Valid Education education) throws UserNotExistException {
        return educationService.addEducationById(userId,education);
    }

    @GetMapping("/users/{userId}/educations")
    // GTB: - 名字粘贴-复制之后忘改了吧 addEducationById
    public List<Education> addEducationById(@PathVariable long userId) throws UserNotExistException {
        return educationService.getEducationById(userId);
    }


}
