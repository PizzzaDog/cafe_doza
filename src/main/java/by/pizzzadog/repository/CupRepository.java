package by.pizzzadog.repository;

import by.pizzzadog.model.Cup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupRepository extends JpaRepository<Cup, Long> {

}
