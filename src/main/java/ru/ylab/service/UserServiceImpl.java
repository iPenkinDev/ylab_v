package ru.ylab.service;

import ru.ylab.model.User;
import ru.ylab.repository.UserRepository;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean registerUser(String username, String email, String password) {
        User user = new User(username, email, password);
        return userRepository.saveUser(user);
    }

    @Override
    public User authenticateUser(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }
}
