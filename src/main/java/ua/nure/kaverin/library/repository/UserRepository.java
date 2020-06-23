package ua.nure.kaverin.library.repository;

import ua.nure.kaverin.library.model.User;

public interface UserRepository {

    User getUserByLogin(String login);

    void createUser(User user);
}
