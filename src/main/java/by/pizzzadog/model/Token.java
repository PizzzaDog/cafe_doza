package by.pizzzadog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "token")
@Data
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue
    private Long id;

    private String value;

    private LocalDateTime createDate;

    private LocalDateTime deadDate;
}
