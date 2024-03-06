package application.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "membre")
public class Membre {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  @Column(name = "nom")
  private String nom;
  @Column(name = "prenom")
  private String prenom;
  @Column(name = "adresse")
  private String adresse;
  @Column(name = "dateNaissance")
  private LocalDateTime dateNaissance;
  @Column(name = "email")
  private String email;
  @Column(name = "motDePasse")
  private String motDePasse;
}