package by.pizzzadog.repository;

import by.pizzzadog.model.PersonalQr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrRepository extends JpaRepository<PersonalQr, Integer> {
}
