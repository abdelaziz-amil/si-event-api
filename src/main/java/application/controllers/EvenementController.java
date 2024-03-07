package application.controllers;

import application.dtos.EvenementDto;
import application.dtos.EvenementPostDto;
import application.dtos.MembreInscriptionDto;
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

	@GetMapping
	public List<EvenementDto> getEvenements() {
		return evenementsService.getAllEvenements();
	}

	@GetMapping("/{evenetId}")
	public EvenementDto getEvenements(@PathVariable Long evenetId){
		return evenementsService.getEvenementById(evenetId);
	}

	@PostMapping
	public EvenementDto saveEvenement(@RequestBody EvenementPostDto evenementDto){
		return evenementsService.saveEvenement(evenementDto);
	}

	@DeleteMapping("/{eventId}")
	public ResponseEntity<?> deleteEvenement(@PathVariable Long eventId){
		return evenementsService.deleteEvenement(eventId);
	}

	@PutMapping("/{eventId}")
	public EvenementDto updateEvenement(@PathVariable Long eventId, @RequestBody EvenementPostDto evenementDto){
		return evenementsService.updateEvenement(eventId, evenementDto);
	}

	@GetMapping("/{eventId}/membres")
	public ResponseEntity<?> getEvenementMembres(@PathVariable Long eventId){
		return evenementsService.getEvenementMembres(eventId);
	}

	@PutMapping("/{eventId}/membres")
	public ResponseEntity<?> addMembreToEvenement(@PathVariable Long eventId, @RequestBody MembreInscriptionDto membre){
		return evenementsService.addMembreToEvenement(eventId, membre);
	}

	@DeleteMapping("/{eventId}/membres/{membreId}")
	public ResponseEntity<?> removeMembreFromEvenement(@PathVariable Long eventId, @PathVariable Long membreId){
		return evenementsService.removeMembreFromEvenement(eventId, membreId);
	}

	@PutMapping("/{eventId}/location")
	public EvenementDto updateLocation(@PathVariable Long eventId, @RequestBody Long location){
		return evenementsService.updateLocation(eventId, location);
	}
}
