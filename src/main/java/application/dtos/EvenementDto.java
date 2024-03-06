package application.dtos;

import application.entities.Membre;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class EvenementDto {

	private Long Id;
	private String title;
	private String description;
	private Long locationId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

}
