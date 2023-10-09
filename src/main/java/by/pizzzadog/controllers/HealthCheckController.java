package by.pizzzadog.controllers;

import by.pizzzadog.model.MyUser;
import by.pizzzadog.service.QRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class HealthCheckController {
    private final QRService qrService;

    @GetMapping("/alive")
    public String isAlive() {
        return "Alive";
    }

    @GetMapping("/fuck_off")
    public String checkQR() {
        return "Nice QR scanned, Ulyana loh btw";
    }

    @GetMapping("/qr")
    public ResponseEntity getQr() throws Exception {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrService.generateQRCodeImage("qwe"));
    }

    @GetMapping("/qr2")
    public byte[] getQr2() throws Exception {
        return qrService.generateQRCodeImage("qwe");
    }

    @GetMapping("/get_user")
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
