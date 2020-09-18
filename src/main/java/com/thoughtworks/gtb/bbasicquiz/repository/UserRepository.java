package com.thoughtworks.gtb.bbasicquiz.repository;

import com.thoughtworks.gtb.bbasicquiz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {

    Optional<User> findById(long id);

    long count();

    User save(User user);
}
