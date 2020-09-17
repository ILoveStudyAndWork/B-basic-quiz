package com.thoughtworks.gtb.bbasicquiz.repository;

import com.thoughtworks.gtb.bbasicquiz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static List<User> users = new ArrayList<>();


    @Override
    public User findById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                // GTB: orElse 这里直接抛异常行不？
                // GTB: 这里直接返回 Optional 行不？
                .orElse(null);
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
