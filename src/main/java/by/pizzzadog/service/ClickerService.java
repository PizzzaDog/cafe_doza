package by.pizzzadog.service;

import by.pizzzadog.dto.request.ClickerGameFinishRequest;
import by.pizzzadog.model.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClickerService {
    private final UserService userService;
    
    public Integer getRecordAfterPlay(ClickerGameFinishRequest request) {
        MyUser user = userService.getValidUserByEmailAndToken(request.getEmail(), request.getToken());
        if (request.getScore() == null) {
            throw new RuntimeException("Score is null");
        }
        Integer record = user.getRecord() > request.getScore() ?
                user.getRecord() : request.getScore();
        user.setRecord(record);
        userService.save(user);
        return record;
    }
}
