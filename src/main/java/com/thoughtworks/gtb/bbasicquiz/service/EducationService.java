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

    // GTB: - 跟getEducationById有很多重复代码，能优化不？
    public Education addEducationById(long userId, Education education) throws UserNotExistException {
        User user = userRepository.findById(userId);
        if (user == null){
            throw new UserNotExistException("",userId);
        }
        education.setUserId(userId);
        return educationRepository.save(education);
    }

    public List<Education> getEducationById(long userId) throws UserNotExistException {
        User user = userRepository.findById(userId);
        if (user == null){
            throw new UserNotExistException(ExceptionFromConstants.EDUCATION_INFO,userId);
        }
        return educationRepository.findAllByUserId(userId);
    }
}
