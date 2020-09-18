package com.thoughtworks.gtb.bbasicquiz.repository;

import com.thoughtworks.gtb.bbasicquiz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final List<User> users = new ArrayList<>();


    @Override
    public Optional<User> findById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public long count() {
        return users.size();
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

}
