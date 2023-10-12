package by.pizzzadog.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogoutRequest {

    private String email;
    private String token;

}
