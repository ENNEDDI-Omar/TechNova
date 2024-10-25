package org.technova.service.impl;

import org.technova.model.User;
import org.technova.repository.UserRepository;
import org.technova.service.interfaces.UserService;

import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    // Pour l'injection par constructeur
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Pour l'injection par setter
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Pour l'injection par interface
    public void setRepository(UserRepository repository) {
        this.userRepository = repository;
    }

    // Constructeur par défaut nécessaire pour Spring
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
    public List<User> getExpiredUsers(LocalDateTime date) {
        return userRepository.findExpiredUsers(date);
    }

}