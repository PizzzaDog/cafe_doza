package by.pizzzadog.controllers;

import by.pizzzadog.dto.SessionUserDto;
import by.pizzzadog.dto.request.BonusUserDto;
import by.pizzzadog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BonusController extends BaseController{
    private final UserService userService;

    @PostMapping("/cups/add")
    public SessionUserDto addCupsToUser(@RequestBody BonusUserDto bonusUserDto) {
        return userService.addCupsToUser(bonusUserDto);
    }

    @PostMapping("/gifts/remove")
    public SessionUserDto removeGiftsFromUser() {
        return null;
    }
}
