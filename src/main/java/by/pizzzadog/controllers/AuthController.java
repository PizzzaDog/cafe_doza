package by.pizzzadog.controllers;

import by.pizzzadog.dto.SessionUserDto;
import by.pizzzadog.dto.request.RegisterUserDto;
import by.pizzzadog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AuthController extends BaseController {
    private final UserService userService;

    @PostMapping("/registration")
    public SessionUserDto registerUser(@RequestBody RegisterUserDto user) {
        log.info("POST: /registration");
        return userService.registerUser(user);
    }
}
