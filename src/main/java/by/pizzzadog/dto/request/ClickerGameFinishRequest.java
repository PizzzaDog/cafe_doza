package by.pizzzadog.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClickerGameFinishRequest {
    private Integer score;
    private String email;
    private String token;
}
