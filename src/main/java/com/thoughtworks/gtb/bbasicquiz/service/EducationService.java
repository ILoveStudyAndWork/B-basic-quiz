package com.thoughtworks.gtb.bbasicquiz.service;

import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionFromConstants;
import com.thoughtworks.gtb.bbasicquiz.domain.Education;
import com.thoughtworks.gtb.bbasicquiz.domain.User;
import com.thoughtworks.gtb.bbasicquiz.exception.UserNotExistException;
import com.thoughtworks.gtb.bbasicquiz.repository.EducationRepository;
import com.thoughtworks.gtb.bbasicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private final UserRepository userRepository;
    private final EducationRepository educationRepository;

    public EducationService(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }

    public Education addEducationById(Long userId, Education education) throws UserNotExistException {
        checkUserExist(userId);
        userRepository.findById(userId).ifPresent(education::setUser);
        return educationRepository.save(education);
    }

    public List<Education> getEducationById(Long userId) throws UserNotExistException {
        checkUserExist(userId);
        return educationRepository.findAllByUserId(userId);
    }

    public void checkUserExist(Long userId) throws UserNotExistException {
        userRepository.findById(userId).orElseThrow(() -> new UserNotExistException(ExceptionFromConstants.EDUCATION_INFO, userId));
    }
}
