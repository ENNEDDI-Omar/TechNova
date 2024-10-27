package org.technova.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.technova.model.User;
import org.technova.service.interfaces.UserService;  // Important : utilisez l'interface correcte
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class UserController extends AbstractController {

    private UserService userService;  // Utilisez l'interface

    // Setter pour l'injection
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        String action = request.getParameter("action");
        ModelAndView modelAndView = new ModelAndView();

        if (action == null || action.equals("list")) {
            modelAndView.setViewName("userList");
            modelAndView.addObject("users", userService.getAllUsers());
        } else if (action.equals("add")) {
            if ("POST".equals(request.getMethod())) {
                User user = new User();
                user.setNom(request.getParameter("nom"));
                user.setPrenom(request.getParameter("prenom"));
                user.setPieceIdentite(request.getParameter("pieceIdentite"));
                user.setNationalite(request.getParameter("nationalite"));
                user.setDateInscription(LocalDateTime.now());
                user.setDateExpiration(LocalDateTime.now().plusYears(1));

                userService.addUser(user);
                response.sendRedirect(request.getContextPath() + "/users");
                return null;
            }
            modelAndView.setViewName("userForm");
        } else if (action.equals("edit")) {
            if ("POST".equals(request.getMethod())) {
                Long id = Long.parseLong(request.getParameter("id"));
                User user = userService.getUserById(id);
                user.setNom(request.getParameter("nom"));
                user.setPrenom(request.getParameter("prenom"));
                user.setNationalite(request.getParameter("nationalite"));

                userService.updateUser(user);
                response.sendRedirect(request.getContextPath() + "/users");
                return null;
            }
            Long id = Long.parseLong(request.getParameter("id"));
            modelAndView.addObject("user", userService.getUserById(id));
            modelAndView.setViewName("userForm");
        } else if (action.equals("delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            userService.deleteUser(id);
            response.sendRedirect(request.getContextPath() + "/users");
            return null;
        }

        return modelAndView;
    }
}
