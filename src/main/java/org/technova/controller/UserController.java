package org.technova.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.technova.model.User;
import org.technova.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserController extends AbstractController {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        String action = request.getParameter("action");
        String contextPath = request.getContextPath();

        if (action == null || action.equals("list")) {
            ModelAndView modelAndView = new ModelAndView("userList");
            modelAndView.addObject("users", userService.getAllUsers());
            return modelAndView;
        }

        if (action.equals("add")) {
            if ("POST".equals(request.getMethod())) {
                // Validation des données
                String pieceIdentite = request.getParameter("pieceIdentite");
                if (userService.getUserByPieceIdentite(pieceIdentite) != null) {
                    ModelAndView modelAndView = new ModelAndView("userForm");
                    modelAndView.addObject("error", "Cette pièce d'identité existe déjà");
                    return modelAndView;
                }

                User user = new User();
                user.setNom(request.getParameter("nom"));
                user.setPrenom(request.getParameter("prenom"));
                user.setPieceIdentite(pieceIdentite);
                user.setNationalite(request.getParameter("nationalite"));
                user.setDateInscription(LocalDateTime.now());
                user.setDateExpiration(LocalDateTime.now().plusYears(1));

                userService.addUser(user);
                response.sendRedirect(contextPath + "/users");
                return null;
            }
            return new ModelAndView("userForm");
        }

        if (action.equals("edit")) {
            Long id = Long.parseLong(request.getParameter("id"));

            if ("POST".equals(request.getMethod())) {
                User user = userService.getUserById(id);
                if (user != null) {
                    user.setNom(request.getParameter("nom"));
                    user.setPrenom(request.getParameter("prenom"));
                    user.setNationalite(request.getParameter("nationalite"));

                    userService.updateUser(user);
                    response.sendRedirect(contextPath + "/users");
                    return null;
                }
            }

            ModelAndView modelAndView = new ModelAndView("userForm");
            modelAndView.addObject("user", userService.getUserById(id));
            return modelAndView;
        }

        if (action.equals("delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            userService.deleteUser(id);
            response.sendRedirect(contextPath + "/users");
            return null;
        }

        // Si aucune action valide n'est trouvée, redirection vers la liste
        response.sendRedirect(contextPath + "/users");
        return null;
    }
}