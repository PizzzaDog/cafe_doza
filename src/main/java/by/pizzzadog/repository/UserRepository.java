package by.pizzzadog.repository;

import by.pizzzadog.model.MyUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, String> {


    @EntityGraph(attributePaths = "token")
    Optional<MyUser> findByEmail(String email);
}
