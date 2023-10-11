package by.pizzzadog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MyUser {
    private String name;
//    private String email;
    private String role;
    private Integer cups;
    private Integer gifts;
    private String token;
    private String  qrUrl;
}
