package ru.ylab.repository;

import ru.ylab.model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users;

    public UserRepository() {
        users = new HashMap<>();
    }

    public boolean saveUser(User user) {
        if (users.containsKey(user.getEmail())) {
            return false;
        }
        users.put(user.getEmail(), user);
        return true;
    }

    public User findUserByEmail(String email) {
        return users.get(email);
    }
}
