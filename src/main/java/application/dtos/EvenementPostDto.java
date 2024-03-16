package application.dtos;

import lombok.Data;

import java.time.LocalDateTime;

/*
JSON Pour testser requête POST
{

	"titre": "titre",
	"description": "description",
	"lieuId": 1,
	"dateHeureDebut": "2021-06-01T10:15:30",
	"dateHeureFin": "2021-06-01T10:15:30"
}
 */
@Data
public class EvenementPostDto {

	private String titre;
	private String description;
	private Long lieuId;
	private LocalDateTime dateHeureDebut;
	private LocalDateTime dateHeureFin;

}
