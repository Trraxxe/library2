package ua.nure.kaverin.library.repository.stub;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ua.nure.kaverin.library.model.User;
import ua.nure.kaverin.library.model.UserRole;
import ua.nure.kaverin.library.repository.UserRepository;

@Repository
@Primary
public class StubUserRepository implements UserRepository {
    @Override
    public User getUserByLogin(String login) {
        return new User(null, login, null, "password", UserRole.ADMIN, false);
    }

    @Override
    public void createUser(User user) {

    }
}
