package ua.nure.kaverin.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.util.StringUtils;
import ua.nure.kaverin.library.model.User;
import ua.nure.kaverin.library.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public View loginUser(@RequestParam String login, @RequestParam String password) {
        User user = userService.getUserByLogin(login);
        if (user != null && StringUtils.equals(password, user.getPassword())) {
            return new RedirectView("/books");
        }
        return new RedirectView("/");
    }
}
