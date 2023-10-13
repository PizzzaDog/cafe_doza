package by.pizzzadog.service;

import by.pizzzadog.dto.SessionUserDto;
import by.pizzzadog.dto.request.BonusUserDto;
import by.pizzzadog.dto.request.LoginRequestDto;
import by.pizzzadog.dto.request.LogTokenRequest;
import by.pizzzadog.dto.request.RegisterUserDto;
import by.pizzzadog.dto.response.RoleResponse;
import by.pizzzadog.exception.AuthException;
import by.pizzzadog.mapper.UserMapper;
import by.pizzzadog.model.Cup;
import by.pizzzadog.model.MyUser;
import by.pizzzadog.model.PersonalQr;
import by.pizzzadog.model.Token;
import by.pizzzadog.repository.CupRepository;
import by.pizzzadog.repository.QrRepository;
import by.pizzzadog.repository.RoleRepository;
import by.pizzzadog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final QRService qrService;
    private final QrRepository qrRepository;
    private final UserMapper userMapper;
    private final JwtService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CupRepository cupRepository;

    public SessionUserDto registerUser(RegisterUserDto userDto) {
        MyUser user = new MyUser();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getName());
        user.setPassword(encryptPass(userDto.getPassword()));
        user.setRole(roleRepository.getById(1));
        user.setGifts(0);
        user.setRecord(0);
        user.setCreateDate(LocalDateTime.now());
        user.setQr(qrService.generateQr(user));
        user.setToken(tokenService.generateToken(user).getValue());
        return userMapper.toSessionUserDto(userRepository.save(user));
    }

    private String encryptPass(String pass) {
        return passwordEncoder.encode(pass);
    }

    public boolean logout(LogTokenRequest logTokenRequest) {
        MyUser user = getValidUserByEmailAndToken(logTokenRequest.getEmail(), logTokenRequest.getToken());
        tokenService.disableToken(tokenService.getByUserOrGenerate(user));
        user.setToken(null);
        userRepository.save(user);
        return true;
    }

    public SessionUserDto loginUser(LoginRequestDto loginDto) {
        MyUser user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new AuthException("User with this email not found " + loginDto.getEmail()));
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password for user " + loginDto.getEmail());
        }
        Token token = tokenService.getByUserOrGenerate(user);
        user.setToken(token.getValue());
        userRepository.save(user);
        LocalDateTime deadDate = token.getDeadDate();

        if (deadDate != null && deadDate.isBefore(LocalDateTime.now())) {
            tokenService.disableToken(token);
        }
        return userMapper.toSessionUserDto(user);
    }

    public MyUser getValidUserByEmailAndToken(String email, String tokenStr) {
        MyUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException("VALID User with this email not found " + email));
        Token token = tokenService.getByUserOrGenerate(user);
        boolean isValid = tokenStr.equals(token.getValue());
        if (!isValid) {
            throw new AuthException("User found but have wrong token");
        }
        return user;
    }

    public void save(MyUser user) {
        userRepository.save(user);
    }

    public RoleResponse getRole(LogTokenRequest request) {
        MyUser user = getValidUserByEmailAndToken(request.getEmail(), request.getToken());
        return new RoleResponse(user.getRole().getName());
    }

    public SessionUserDto getByCodeAndAuth(Long qrCode, LogTokenRequest auth) {
        MyUser user = getValidUserByEmailAndToken(auth.getEmail(), auth.getToken());
        if ("ADMIN".equals(user.getRole().getName())) {
            PersonalQr qrForUser = qrRepository.findByCode(qrCode)
                    .orElseThrow(() -> new RuntimeException("No user found by qr"));
            return userMapper.toSessionUserDto(qrForUser.getUser());
        }
        return null;
    }

    public SessionUserDto refreshSessionUser(LogTokenRequest logTokenRequest) {
        MyUser user = getValidUserByEmailAndToken(logTokenRequest.getEmail(),
                logTokenRequest.getToken());
        return userMapper.toSessionUserDto(user);
    }

    public SessionUserDto addCupsToUser(BonusUserDto bonusUserDto) {
        RoleResponse role = getRole(new LogTokenRequest(bonusUserDto.getAdminEmail(), bonusUserDto.getAdminToken()));
        if (!"ADMIN".equals(role.getRole())) {
            throw new RuntimeException("ACCESS DENIED");
        }
        MyUser user = userRepository.findByEmail(bonusUserDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found by email " + bonusUserDto.getEmail()));
        //TODO check name
        Integer currentCups = user.getCups();
        currentCups += bonusUserDto.getCount();
        while (currentCups >= 10) {
            user.setGifts(user.getGifts() + 1);
            currentCups -= 10;
        }
        user.setCups(currentCups);
        Cup cup = new Cup();
        cup.setCount(bonusUserDto.getCount());
        cup.setOwner(user);
        cup.setCreateDate(LocalDateTime.now());
        cupRepository.save(cup);
        userRepository.save(user);
        return userMapper.toSessionUserDto(user);
    }

    public SessionUserDto removeGiftsFromUser(BonusUserDto bonusUserDto) {
        RoleResponse role = getRole(new LogTokenRequest(bonusUserDto.getAdminEmail(), bonusUserDto.getAdminToken()));
        if (!"ADMIN".equals(role.getRole())) {
            throw new RuntimeException("ACCESS DENIED");
        }
        MyUser user = userRepository.findByEmail(bonusUserDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found by email " + bonusUserDto.getEmail()));
        Integer count = bonusUserDto.getCount();
        if (user.getGifts() < count) {
            throw new RuntimeException("Not enough gifts for user " + bonusUserDto.getName() + bonusUserDto.getEmail());
        }
        user.setGifts(user.getGifts() - count);
        return userMapper.toSessionUserDto(userRepository.save(user));
    }
}
