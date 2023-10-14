package by.pizzzadog.controllers;

import by.pizzzadog.dto.SessionUserDto;
import by.pizzzadog.dto.request.LoginRequestDto;
import by.pizzzadog.dto.request.LogTokenRequest;
import by.pizzzadog.dto.request.RegisterUserDto;
import by.pizzzadog.dto.response.RoleResponse;
import by.pizzzadog.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/login")
    public SessionUserDto login(@RequestBody LoginRequestDto loginDto) {
        log.info("POST: /login");
        return userService.loginUser(loginDto);
    }

    @PostMapping("/logout")
    public Boolean logout(@RequestBody LogTokenRequest logTokenRequest) {
        log.info("POST: /logout");
        return userService.logout(logTokenRequest);
    }

    @PostMapping("/refresh")
    public SessionUserDto refreshSession(@RequestBody LogTokenRequest logTokenRequest) {
        return userService.refreshSessionUser(logTokenRequest);
    }


    @PostMapping("/role")
    public RoleResponse getUserRole(@RequestBody LogTokenRequest request) {
        return userService.getRole(request);
    }
}
