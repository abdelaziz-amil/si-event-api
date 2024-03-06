package application.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "membre")
public class Membre {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  private String nom;
  private String prenom;
  private String adresse;
  private LocalDateTime dateNaissance;
  private String mail;
  private String motDePasse;
}