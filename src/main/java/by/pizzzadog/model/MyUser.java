package by.pizzzadog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private Long id;

    private String email;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Cup> cups;

    @Basic
    private Integer cupsCount;

    private Integer gifts;

    private String token;

    @Basic
    private Integer qrId;

    private LocalDateTime createDate;
}
