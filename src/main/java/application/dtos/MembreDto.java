package application.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MembreDto {
  private Long Id;
  private String nom;
  private String prenom;
  private String adresse;
  private LocalDateTime dateNaissance;
  private String mail;
  private String motDePasse;
}
