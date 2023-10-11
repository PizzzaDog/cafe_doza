package by.pizzzadog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
public class MyUser {

    @Id
    private String id;

    private String email;

    private String username;

    private String password;

    private String role;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Cup> cups;

    private Integer gifts;

    private String token;

    private String qrUrl;

    private LocalDateTime createDate;
}
