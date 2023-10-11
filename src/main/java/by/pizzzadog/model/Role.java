package by.pizzzadog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role {

    @Id
    private Integer id;
    private String name;
}
