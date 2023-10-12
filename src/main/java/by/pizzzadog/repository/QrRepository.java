package by.pizzzadog.repository;

import by.pizzzadog.model.PersonalQr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QrRepository extends JpaRepository<PersonalQr, Integer> {

    PersonalQr getByUserId(@Param("userId") Long userId);

    Optional<PersonalQr> findByCode(Long qrCode);
}
