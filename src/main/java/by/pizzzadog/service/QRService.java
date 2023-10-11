package by.pizzzadog.service;

import by.pizzzadog.model.MyUser;
import by.pizzzadog.model.PersonalQr;
import by.pizzzadog.repository.QrRepository;
import lombok.RequiredArgsConstructor;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
//TODO migrate to front
public class QRService {

    private final QrRepository qrRepository;
    public byte[] generateQRCodeImage(String barcodeText) throws Exception {
//        barcodeText = "http://45.82.71.93:8088/fuck_off";
        barcodeText = "65749302043";
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

        return stream.toByteArray();
    }

    public Integer generateQr(MyUser user) {
        PersonalQr personalQr = new PersonalQr();
        LocalDateTime now = LocalDateTime.now();
        String codeStr = "" + now.getYear() + now.getDayOfYear() + now.getHour() + now.getMinute() + now.getSecond();
        Long code = Long.parseLong(codeStr);
        ByteArrayOutputStream stream = QRCode
                .from(codeStr)
                .withSize(250, 250)
                .stream();
        personalQr.setUser(user);
        personalQr.setCode(code);
        personalQr.setData(stream.toByteArray());
        personalQr.setCreateDate(now);
        PersonalQr save = qrRepository.save(personalQr);
        return save.getId();
    }

    public String getById(Integer qrId) {
        return qrRepository.findById(qrId).get().getUrl();
    }

    public byte[] getByUserId(Long userId) {
        return qrRepository.getByUserId(userId).getData();
    }
}
