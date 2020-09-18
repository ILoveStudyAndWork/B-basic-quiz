package com.thoughtworks.gtb.bbasicquiz.service;

import com.thoughtworks.gtb.bbasicquiz.domain.Education;
import com.thoughtworks.gtb.bbasicquiz.domain.User;
import com.thoughtworks.gtb.bbasicquiz.exception.UserNotExistException;
import com.thoughtworks.gtb.bbasicquiz.repository.EducationRepository;
import com.thoughtworks.gtb.bbasicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    private final UserRepository userRepository;
    private final EducationRepository educationRepository;

    public EducationService(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }

    public Education addEducationById(long userId, Education education) throws UserNotExistException {
        checkUserExist(userId);
        education.setUserId(userId);
        return educationRepository.save(education);
    }

    public List<Education> getEducationById(long userId) throws UserNotExistException {
        checkUserExist(userId);
        return educationRepository.findAllByUserId(userId);
    }

    private void checkUserExist(long userId) throws UserNotExistException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()){
            throw new UserNotExistException("", userId);
        }
    }
}
