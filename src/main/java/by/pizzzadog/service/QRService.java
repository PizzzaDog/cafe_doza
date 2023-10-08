package by.pizzzadog.service;

import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class QRService {
    public byte[] generateQRCodeImage(String barcodeText) throws Exception {
        barcodeText = "http://45.82.71.93:8088/fuck_off";
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

        return stream.toByteArray();
    }
}
