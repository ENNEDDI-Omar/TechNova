package org.technova.service.interfaces;

import org.technova.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    // Opérations CRUD de base
    User addUser(User user);
    User updateUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);

    // Méthodes métier spécifiques
    List<User> getUsersByNationalite(String nationalite);
    User getUserByPieceIdentite(String pieceIdentite);
}