package by.pizzzadog.service;

import by.pizzzadog.model.MyUser;
import by.pizzzadog.model.PersonalQr;
import by.pizzzadog.model.Token;
import by.pizzzadog.repository.QrRepository;
import by.pizzzadog.repository.TokenRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final QrRepository qrRepository;
    private final TokenRepository tokenRepository;


    public String generateToken(MyUser user) {
        PersonalQr personalQr = qrRepository.findById(user.getQrId()).get();
        String tokenStr = Arrays.toString(Base64Coder.encode(personalQr.getCode().toString().getBytes()));
        Token token = new Token();
        token.setCreateDate(LocalDateTime.now());
        token.setValue(tokenStr);
        tokenRepository.save(token);
        return tokenStr;
    }

}
