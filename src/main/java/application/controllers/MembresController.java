package application.controllers;

import application.dtos.MembresDto;
import org.springframework.web.bind.annotation.*;

import application.services.impl.MembresServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/membres")
public class MembresController {
	
	private final MembresServiceImpl membresService;

	public MembresController(MembresServiceImpl membresService) {
		this.membresService = membresService;
	}

	/**
	 * <p>Get all members in the system</p>
	 * @return List<MembresDto>
	 */
	@GetMapping
	public List<MembresDto> getMembres() {
		return membresService.getAllMembres();
	}

	/**
	 * Method to get the member based on the ID
	 */
	@GetMapping("/{membreId}")
	public MembresDto getMembre(@PathVariable Long membreId){
		return membresService.getMembreById(membreId);
	}

	/**
	 * Create a new Membres in the system
	 */
	@PostMapping
	public MembresDto saveMembre(final @RequestBody MembresDto membresDto){
		return membresService.saveMembre(membresDto);
	}

	/**
	 * Delete a member by it's id
	 */
	@DeleteMapping("/{membreId}")
	public Boolean deleteMembre(@PathVariable Long membreId){
		return membresService.deleteMembre(membreId);
	}
	
}
