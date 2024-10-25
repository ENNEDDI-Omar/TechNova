package org.technova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotBlank(message = "Le prenom est obligatoire")
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotBlank(message = "La Pieçe d'identité est obligatoire")
    @Column(name = "piece_identite", nullable = false, unique = true)
    private String pieceIdentite;

    @NotBlank(message = "La Nationalité est obligatoire")
    @Column(name = "nationalite", nullable = false)
    private String nationalite;

    @NotNull(message = "La Date d'inscription est obligatoire")
    @Column(name = "date_inscription", nullable = false)
    private LocalDateTime dateInscription;

    @Column(name = "date_expiration", nullable = false)
    private LocalDateTime dateExpiration;

    //Methode pour initialiser la date d'inscription et la date d'expiration lors de la creation d'un utilisateur
    @PrePersist
    public void prePersist()
    {
        if (dateInscription == null)
        {
            dateInscription = LocalDateTime.now();
        }
        if (dateExpiration == null)
        {
            dateExpiration = dateInscription.plusYears(1);
        }
    }




}
