package by.pizzzadog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QrRequestDto {
    private String email;
    private String token;
}
