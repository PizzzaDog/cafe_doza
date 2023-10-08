package by.pizzzadog.controllers;

import by.pizzzadog.service.QRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;

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

}
