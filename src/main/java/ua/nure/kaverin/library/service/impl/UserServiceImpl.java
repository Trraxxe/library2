package ua.nure.kaverin.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.kaverin.library.model.User;
import ua.nure.kaverin.library.repository.UserRepository;
import ua.nure.kaverin.library.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public void createUser(User user) {
        userRepository.createUser(user);
    }
}
