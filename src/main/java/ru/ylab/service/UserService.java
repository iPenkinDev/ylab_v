package ru.ylab.service;

import ru.ylab.model.User;

public interface UserService {

    boolean registerUser(String username, String email, String password);
    User authenticateUser(String email, String password);
}
