package org.technova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.technova.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // Recherche par nationalité
    List<User> findByNationalite(String nationalite);

    // Recherche par pièce d'identité
    User findByPieceIdentite(String pieceIdentite);

}