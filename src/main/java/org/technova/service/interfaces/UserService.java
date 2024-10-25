package org.technova.service.interfaces;

import org.technova.model.User;
import org.technova.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    User addUser(User user);
    User updateUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);

    // Méthodes additionnelles
    List<User> getUsersByNationalite(String nationalite);
    List<User> getExpiredUsers(LocalDateTime date);
    //User getUserByPieceIdentite(String pieceIdentite);


}