package by.pizzzadog.controllers;

import by.pizzzadog.dto.SessionUserDto;
import by.pizzzadog.dto.request.LogTokenRequest;
import by.pizzzadog.model.QrRequestDto;
import by.pizzzadog.service.QRService;
import by.pizzzadog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
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
        log.info("POST ADMIN QR " + auth.getEmail() + auth.getToken() + qrCode);
        return userService.getByCodeAndAuth(qrCode, auth);
    }
}
