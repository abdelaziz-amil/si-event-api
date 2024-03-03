package application.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Evenements {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String title;
	private String description;
	private Long locationId;
	private LocalDate startTime;
	private LocalDate endTime;
	@ManyToMany
	@JoinTable(
					name = "evenements_membres",
					joinColumns = @JoinColumn(name = "evenements_id"),
					inverseJoinColumns = @JoinColumn(name = "membres_id")
	)
	private List<Membres> membres = new ArrayList<Membres>();
}
