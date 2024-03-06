package application.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "evenement")
public class Evenement {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(name = "titre")
	private String title;
	private String description;
	@Column(name = "lieuId")
	private Long locationId;
	@Column(name = "dateHeureDebut")
	private LocalDateTime startTime;
	@Column(name = "dateHeureFin")
	private LocalDateTime endTime;
}
