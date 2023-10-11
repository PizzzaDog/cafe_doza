package by.pizzzadog.repository;

import by.pizzzadog.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, String> {


}
