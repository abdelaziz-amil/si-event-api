package application.dtos;

import application.entities.Membre;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class EvenementDto {

	private Long Id;
	private String title;
	private String description;
	private Long locationId;
	private LocalDate startTime;
	private LocalDate endTime;
	private List<Membre> membres = new ArrayList<>();

}
