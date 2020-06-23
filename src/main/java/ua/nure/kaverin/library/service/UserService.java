package ua.nure.kaverin.library.service;

import ua.nure.kaverin.library.model.User;

public interface UserService {

    User getUserByLogin(String login);

    void createUser(User user);
}
