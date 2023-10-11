package by.pizzzadog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SessionUserDto {
    private String name;
    private String token;
    private Integer gifts;
    private Integer cups;
    private String qrUrl;
}
