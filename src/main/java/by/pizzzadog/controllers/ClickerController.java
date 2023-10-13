package by.pizzzadog.controllers;

import by.pizzzadog.dto.request.ClickerGameFinishRequest;
import by.pizzzadog.service.ClickerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClickerController extends BaseController {
    private final ClickerService clickerService;

    @PostMapping("/clicker/finish")
    public Integer finishGame(@RequestBody ClickerGameFinishRequest requestDto) {
        return clickerService.getRecordAfterPlay(requestDto);
    }
}
