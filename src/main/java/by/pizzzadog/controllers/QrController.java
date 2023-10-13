package by.pizzzadog.controllers;

import by.pizzzadog.dto.SessionUserDto;
import by.pizzzadog.dto.request.LogTokenRequest;
import by.pizzzadog.model.QrRequestDto;
import by.pizzzadog.service.QRService;
import by.pizzzadog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QrController extends BaseController {
    private final QRService qrService;
    private final UserService userService;


    @GetMapping("/qr/{qrCode}")
    public ResponseEntity getQr(@PathVariable Long qrCode) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrService.getByCode(qrCode));
    }

    @PostMapping("/qr/get_user/{qrCode}")
    public SessionUserDto getUserByCode(@RequestBody LogTokenRequest auth, @PathVariable Long qrCode) {
        return userService.getByCodeAndAuth(qrCode, auth);
    }
}
