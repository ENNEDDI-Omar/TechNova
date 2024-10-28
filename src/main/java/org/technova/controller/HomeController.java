package org.technova.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.technova.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class HomeController extends AbstractController {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView("home");

        // Message de bienvenue
        modelAndView.addObject("message", "Bienvenue dans le système de gestion des utilisateurs TechNova");

        // Statistiques générales
        long totalUsers = userService.getAllUsers().size();
        modelAndView.addObject("totalUsers", totalUsers);

        // Utilisateurs expirés
//        long expiredUsers = userService.getExpiredUsers(LocalDateTime.now()).size();
//        modelAndView.addObject("expiredUsers", expiredUsers);

        // Statistiques par nationalité
        Map<String, Long> nationaliteStats = userService.getAllUsers().stream()
                .collect(Collectors.groupingBy(
                        user -> user.getNationalite(),
                        Collectors.counting()
                ));
        modelAndView.addObject("nationaliteStats", nationaliteStats);

        return modelAndView;
    }
}