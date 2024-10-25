package org.technova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.technova.model.User;
import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Méthodes fournies automatiquement par Spring Data JPA:
    // findAll(), findById(), save(), delete(), etc.

    // Méthodes personnalisées basées sur le nom
    List<User> findByNationalite(String nationalite);

    List<User> findByDateInscriptionBetween(LocalDateTime debut, LocalDateTime fin);

    // Exemple avec JPQL
    @Query("SELECT u FROM User u WHERE u.dateExpiration < :date")
    List<User> findExpiredUsers(@Param("date") LocalDateTime date);

}