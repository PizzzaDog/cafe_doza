package by.pizzzadog.mapper;

import by.pizzzadog.dto.SessionUserDto;
import by.pizzzadog.model.MyUser;
import by.pizzzadog.service.QRService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final QRService qrService;

    public SessionUserDto toSessionUserDto(MyUser user) {
        SessionUserDto sessionUserDto = new SessionUserDto();
        sessionUserDto.setName(user.getUsername());
        sessionUserDto.setQrUrl(qrService.getById(user.getQrId()));
        sessionUserDto.setToken(user.getToken());
        sessionUserDto.setGifts(user.getGifts());
        //TODO
        sessionUserDto.setCups(0);
        return sessionUserDto;
    }
}
