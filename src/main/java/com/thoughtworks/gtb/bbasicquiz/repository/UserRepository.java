package com.thoughtworks.gtb.bbasicquiz.repository;

import com.thoughtworks.gtb.bbasicquiz.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User findById(long id);

    long count();

    User save(User user);
}
