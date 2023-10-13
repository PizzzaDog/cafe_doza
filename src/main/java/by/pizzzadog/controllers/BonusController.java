package by.pizzzadog.controllers;

import by.pizzzadog.dto.SessionUserDto;
import by.pizzzadog.dto.request.BonusUserDto;
import by.pizzzadog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class BonusController extends BaseController{
    private final UserService userService;

    @PostMapping("/cups/add")
    public SessionUserDto addCupsToUser(@RequestBody BonusUserDto bonusUserDto) {
        log.info("REQUEST POST /cups/add" +
                "\n BonusUserDto:" +
                "\n name " + bonusUserDto.getName() +
                "\n email " + bonusUserDto.getEmail() +
                "\n adminEmail " + bonusUserDto.getAdminEmail() +
                "\n adminToken " + bonusUserDto.getAdminToken() +
                "\n count " + bonusUserDto.getCount() +
                "\n adminName " + bonusUserDto.getName());
        return userService.addCupsToUser(bonusUserDto);
    }

    @PostMapping("/gifts/remove")
    public SessionUserDto removeGiftsFromUser(@RequestBody BonusUserDto bonusUserDto) {
        return userService.removeGiftsFromUser(bonusUserDto);
    }
}
