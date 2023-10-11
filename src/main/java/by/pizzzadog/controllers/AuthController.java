package by.pizzzadog.controllers;

import by.pizzzadog.dto.SessionUserDto;
import by.pizzzadog.dto.request.RegisterUserDto;
import by.pizzzadog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final UserService userService;

    @PostMapping("/registration")
    public SessionUserDto registerUser(RegisterUserDto user) {
        return userService.registerUser(user);
    }
}
