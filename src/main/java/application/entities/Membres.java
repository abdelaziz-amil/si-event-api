package application.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Membres {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String nom;
	private String prenom;
	private String adresse;
	private LocalDate dateNaissance;
	private String mail;
	private String motDePasse;
	
}
