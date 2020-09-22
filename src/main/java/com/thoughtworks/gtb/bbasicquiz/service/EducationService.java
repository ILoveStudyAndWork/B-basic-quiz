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

    public Education addEducationById(Long userId, Education education) throws UserNotExistException {
        User user = checkUserExist(userId);
        education.setUser(user);
        return educationRepository.save(education);
    }
    // todo
    public List<Education> getEducationById(Long userId) throws UserNotExistException {
        checkUserExist(userId);
        return educationRepository.findAllByUserId(userId);
    }

    // todo 去耦合
    private User checkUserExist(Long userId) throws UserNotExistException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()){
            throw new UserNotExistException("", userId);
        }
        return user.get();
    }
}
