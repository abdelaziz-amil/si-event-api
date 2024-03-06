package application.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "evenement")
public class Evenement {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "titre")
	private String titre;
	@Column(name = "description")
	private String description;
	@Column(name = "lieuId")
	private Long lieuId;
	@Column(name = "dateHeureDebut")
	private LocalDateTime dateHeureDebut;
	@Column(name = "dateHeureFin")
	private LocalDateTime dateHeureFin;
}
