package by.pizzzadog.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BonusUserDto {
    private String email;
    private String name;
    private Integer count;
}
