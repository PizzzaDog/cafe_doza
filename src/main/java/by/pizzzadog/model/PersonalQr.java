package by.pizzzadog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "personal_qr")
@Data
@NoArgsConstructor
@Entity
public class PersonalQr {
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser user;

    private Long code;

    private byte[] data;

    private LocalDateTime createDate;
}
