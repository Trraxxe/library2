package ua.nure.kaverin.library.repository.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.nure.kaverin.library.model.User;
import ua.nure.kaverin.library.model.UserRole;
import ua.nure.kaverin.library.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String GET_USER_BY_LOGIN = "select u.id, u.login, u.email, u.password, r.name as role, u.banned from users u " +
            "join roles r on u.role_id = r.id where u.login = ?;";

    @Autowired
    private JdbcTemplate template;

    @Override
    public User getUserByLogin(String login) {
        return template.query(con -> con.prepareStatement(GET_USER_BY_LOGIN),
                ps -> ps.setString(1, login),
                rs -> {
                    if (rs.next()) {
                        User user = new User();
                        user.setId(rs.getString("id"));
                        user.setLogin(rs.getString("login"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setRole(UserRole.valueOf(rs.getString("role")));
                        user.setBanned(rs.getBoolean("banned"));
                        return user;
                    }
                    return null;
                });
    }

    @Override
    public void createUser(User user) {

    }
}
