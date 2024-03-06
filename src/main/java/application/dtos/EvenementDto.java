package application.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EvenementDto {

	private Long id;
	private String titre;
	private String description;
	private Long lieuId;
	private LocalDateTime dateHeureDebut;
	private LocalDateTime dateHeureFin;

}
