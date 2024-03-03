package application.controllers;

import application.dtos.EvenementsDto;
import application.entities.Membres;
import application.repositories.MembresRepository;
import org.springframework.web.bind.annotation.*;

import application.services.impl.EvenementsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EvenementsController {
	
	private final EvenementsServiceImpl evenementsService;
	private final MembresRepository membresRepository;

	public EvenementsController(EvenementsServiceImpl evenementsService, MembresRepository membresRepository) {
		this.evenementsService = evenementsService;
		this.membresRepository = membresRepository;
	}

	/**
	 * <p>Get all members in the system</p>
	 * @return List<EvenementsDto>
	 */
	@GetMapping
	public List<EvenementsDto> getEvenements() {
		return evenementsService.getAllEvenements();
	}

	/**
	 * Method to get the member based on the ID
	 */
	@GetMapping("/{evenetId}")
	public EvenementsDto getEvenements(@PathVariable Long evenetId){
		return evenementsService.getEvenementById(evenetId);
	}

	/**
	 * Create a new Evenements in the system
	 */
	@PostMapping
	public EvenementsDto saveEvenement(final @RequestBody EvenementsDto evenementsDto){
		return evenementsService.saveEvenement(evenementsDto);
	}

	/**
	 * Delete a member by it's id
	 */
	@DeleteMapping("/{eventId}")
	public Boolean deleteMembre(@PathVariable Long eventId){
		return evenementsService.deleteEvenement(eventId);
	}

	@PutMapping("/{eventId}")
	public EvenementsDto updateEvenement(@PathVariable Long eventId, @RequestBody EvenementsDto evenementsDto){
		evenementsDto.setId(eventId);
		return evenementsService.saveEvenement(evenementsDto);
	}

	@GetMapping("/{eventId}/membres")
	public List<Membres> getEvenementMembres(@PathVariable Long eventId){
		return evenementsService.getEvenementMembres(eventId);
	}

	@PutMapping("/{eventId}/membres")
	public EvenementsDto addMembreToEvenement(@PathVariable Long eventId, @RequestBody Membres membres){
		membres = membresRepository.findById(membres.getId()).orElseThrow(() -> new RuntimeException("Membre non trouvé"));
		return evenementsService.addMembreToEvenement(eventId, membres);
	}

	@PutMapping("/{eventId}/location")
	public EvenementsDto updateLocation(@PathVariable Long eventId, @RequestBody Long location){
		return evenementsService.updateLocation(eventId, location);
	}

	@DeleteMapping("/{eventId}/location")
	public EvenementsDto deleteLocation(@PathVariable Long eventId){
		return evenementsService.deleteLocation(eventId);
	}

}
