package org.technova.service.impl;

import org.technova.model.User;
import org.technova.repository.UserRepository;
import org.technova.service.interfaces.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    // Constructeur pour l'injection par constructeur
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Setter pour l'injection par setter
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Setter pour l'injection par interface
    public void setRepository(UserRepository repository) {
        this.userRepository = repository;
    }

    // Constructeur par défaut pour Spring
    public UserServiceImpl() {}

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsersByNationalite(String nationalite) {
        return userRepository.findByNationalite(nationalite);
    }

    @Override
    public User getUserByPieceIdentite(String pieceIdentite) {
        return userRepository.findByPieceIdentite(pieceIdentite);
    }

}