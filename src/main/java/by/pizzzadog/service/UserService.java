package by.pizzzadog.service;

import by.pizzzadog.dto.SessionUserDto;
import by.pizzzadog.dto.request.RegisterUserDto;
import by.pizzzadog.mapper.UserMapper;
import by.pizzzadog.model.MyUser;
import by.pizzzadog.repository.RoleRepository;
import by.pizzzadog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public SessionUserDto registerUser(RegisterUserDto userDto) {
        MyUser user = new MyUser();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getName());
        user.setPassword(encryptPass(userDto.getPassword()));
        user.setRole(roleRepository.getById(1));
        user.setGifts(0);
        user.setCreateDate(LocalDateTime.now());
        MyUser save = userRepository.save(user);
        save.setQrId(qrService.generateQr(user));
        user.setToken(tokenService.generateToken(user));
        userRepository.save(save);
        return userMapper.toSessionUserDto(save);
    }

    private String encryptPass(String pass) {
        return pass;
    }
}
