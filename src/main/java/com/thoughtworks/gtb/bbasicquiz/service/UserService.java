package com.thoughtworks.gtb.bbasicquiz.service;

import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionFromConstants;
import com.thoughtworks.gtb.bbasicquiz.domain.User;
import com.thoughtworks.gtb.bbasicquiz.exception.UserNotExistException;
import com.thoughtworks.gtb.bbasicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserById(Long id) throws UserNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent() ){
            throw new UserNotExistException(ExceptionFromConstants.BASIC_INFO,id);
        }
        return user.get();
    }

    public User register(User user) {
        return userRepository.save(user);
    }
}
