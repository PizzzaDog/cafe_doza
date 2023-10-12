package by.pizzzadog.controllers;

import by.pizzzadog.model.QrRequestDto;
import by.pizzzadog.service.QRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QrController extends BaseController {
    private final QRService qrService;

    @GetMapping("/qr/{qrCode}")
    public ResponseEntity getQr(@PathVariable Long qrCode) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrService.getByCode(qrCode));
    }
}
