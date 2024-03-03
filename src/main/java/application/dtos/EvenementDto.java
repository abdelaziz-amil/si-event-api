package application.dtos;

import application.entities.Membres;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class EvenementsDto {

	private Long Id;
	private String title;
	private String description;
	private Long locationId;
	private LocalDate startTime;
	private LocalDate endTime;
	private List<Membres> membres = new ArrayList<>();

}
