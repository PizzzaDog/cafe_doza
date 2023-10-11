package by.pizzzadog.controllers;

import by.pizzzadog.model.MyUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @GetMapping("/get_userrr")
    public MyUser getUser() {
        MyUser user = new MyUser();
        user.setGifts(1);
        user.setCups(7);
        user.setName("Pushka");
        user.setQrUrl("http://45.82.71.93:8088/qr");
        user.setToken("some_mega_token_$$$");
        return user;
    }

}
