package by.pizzzadog.service;

import by.pizzzadog.model.MyUser;
import by.pizzzadog.model.PersonalQr;
import by.pizzzadog.model.Token;
import by.pizzzadog.repository.QrRepository;
import by.pizzzadog.repository.TokenRepository;
import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final QrRepository qrRepository;
    private final TokenRepository tokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${security.salt}")
    private String salt;


    public Token generateToken(MyUser user) {
        PersonalQr personalQr = user.getQr();
        Token token = new Token();
        LocalDateTime now = LocalDateTime.now();

        String tokenStr = passwordEncoder.encode(now + personalQr.getCode().toString() + salt);
        JWT.create()
                .withPayload(Map.of(
                        "token", tokenStr,
                        "createDate", now.toString(),
                        "email", user.getEmail()
                ));
        token.setCreateDate(now);
        token.setValue(tokenStr);
        token.setUser(user);
        token.setIsAlive(true);
        return tokenRepository.save(token);
    }

    public void disableToken(Token token) {
        token.setUser(null);
        token.setIsAlive(false);
        tokenRepository.save(token);
    }

    public Token getByUserOrGenerate(MyUser user) {
        Token token = tokenRepository.findByUserAndIsAliveTrue(user);
        if (token == null) {
            token = generateToken(user);
        }
        return token;
    }
}
