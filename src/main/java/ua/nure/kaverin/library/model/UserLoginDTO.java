package ua.nure.kaverin.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDTO {

    private String login;
    private String password;
}
