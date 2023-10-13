package by.pizzzadog.service;

import by.pizzzadog.dto.SessionUserDto;
import by.pizzzadog.dto.request.LoginRequestDto;
import by.pizzzadog.dto.request.LogoutRequest;
import by.pizzzadog.dto.request.RegisterUserDto;
import by.pizzzadog.exception.AuthException;
import by.pizzzadog.mapper.UserMapper;
import by.pizzzadog.model.MyUser;
import by.pizzzadog.model.Token;
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
    private final UserMapper userMapper;
    private final JwtService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;

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

    public boolean logout(LogoutRequest logoutRequest) {
        MyUser user = getValidUserByEmailAndToken(logoutRequest.getEmail(), logoutRequest.getToken());
        tokenService.disableToken(tokenService.getByUserOrGenerate(user));
        user.setToken(null);
        userRepository.save(user);
        return true;
    }

    public SessionUserDto loginUser(LoginRequestDto loginDto) {
        MyUser user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new AuthException("User with this email not found"));
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
                .orElseThrow(() -> new AuthException("User with this email not found"));
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

    public String getRole(LogoutRequest request) {
        MyUser user = getValidUserByEmailAndToken(request.getEmail(), request.getToken());
        return user.getRole().getName();
    }
}
