package by.pizzzadog.controllers;

import by.pizzzadog.service.QRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QrController extends BaseController {
    private final QRService qrService;

    @GetMapping("/qr/{userId}")
    public byte[] getQr(@PathVariable Long userId) {
        return qrService.getByUserId(userId);
    }
}
