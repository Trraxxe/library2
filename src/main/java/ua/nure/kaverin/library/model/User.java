package ua.nure.kaverin.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String login;
    private String email;
    private String password;
    private UserRole role;
    private boolean banned;
}
