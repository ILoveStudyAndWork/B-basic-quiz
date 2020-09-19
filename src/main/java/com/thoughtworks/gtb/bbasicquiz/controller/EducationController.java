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

//    @PostMapping("/users/{userId}/educations")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Education addEducationById(@PathVariable Long userId, @RequestBody  @Valid Education education) throws UserNotExistException {
//        return educationService.addEducationById(userId,education);
//    }
//
//    @GetMapping("/users/{userId}/educations")
//    public List<Education> getEducationById(@PathVariable Long userId) throws UserNotExistException {
//        return educationService.getEducationById(userId);
//    }
//

}
