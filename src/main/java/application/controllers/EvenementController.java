package application.controllers;

import application.dtos.EvenementDto;
import application.entities.Membre;
import application.repositories.MembreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import application.services.impl.EvenementServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EvenementController {
	
	private final EvenementServiceImpl evenementsService;
	private final MembreRepository membreRepository;

	public EvenementController(EvenementServiceImpl evenementsService, MembreRepository membreRepository) {
		this.evenementsService = evenementsService;
		this.membreRepository = membreRepository;
	}

	/**
	 * <p>Get all members in the system</p>
	 * @return List<EvenementDto>
	 */
	@GetMapping
	public List<EvenementDto> getEvenements() {
		return evenementsService.getAllEvenements();
	}

	/**
	 * Method to get the member based on the ID
	 */
	@GetMapping("/{evenetId}")
	public EvenementDto getEvenements(@PathVariable Long evenetId){
		return evenementsService.getEvenementById(evenetId);
	}

	/**
	 * Create a new Evenement in the system
	 */
	@PostMapping
	public EvenementDto saveEvenement(final @RequestBody EvenementDto evenementDto){
		return evenementsService.saveEvenement(evenementDto);
	}

	/**
	 * Delete a member by it's id
	 */
	@DeleteMapping("/{eventId}")
	public ResponseEntity<?> deleteEvenement(@PathVariable Long eventId){
		return evenementsService.deleteEvenement(eventId);
	}

	@PutMapping("/{eventId}")
	public EvenementDto updateEvenement(@PathVariable Long eventId, @RequestBody EvenementDto evenementDto){
		evenementDto.setId(eventId);
		return evenementsService.saveEvenement(evenementDto);
	}

	@GetMapping("/{eventId}/membres")
	public ResponseEntity<?> getEvenementMembres(@PathVariable Long eventId){
		return evenementsService.getEvenementMembres(eventId);
	}

	@PutMapping("/{eventId}/membres")
	public void addMembreToEvenement(@PathVariable Long eventId, @RequestBody Membre membre){
		membre = membreRepository.findById(membre.getId()).orElseThrow(() -> new RuntimeException("Les données fournies sont invalides"));
		evenementsService.addMembreToEvenement(eventId, membre);
	}

	@DeleteMapping("/{eventId}/membres/{membreId}")
	public void removeMembreFromEvenement(@PathVariable Long eventId, @PathVariable Long membreId){
		evenementsService.removeMembreFromEvenement(eventId, membreId);
	}

	@PutMapping("/{eventId}/location")
	public EvenementDto updateLocation(@PathVariable Long eventId, @RequestBody Long location){
		return evenementsService.updateLocation(eventId, location);
	}

	@DeleteMapping("/{eventId}/location")
	public EvenementDto deleteLocation(@PathVariable Long eventId){
		return evenementsService.deleteLocation(eventId);
	}

}
