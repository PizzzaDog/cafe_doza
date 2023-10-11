package by.pizzzadog.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RegisterUserDto {
    private String name;
    private String email;
    private String password;
}
