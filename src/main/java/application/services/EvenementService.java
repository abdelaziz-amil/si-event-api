package application.services;

import application.dtos.EvenementDto;
import application.entities.Membre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EvenementService {
    /**
     * Sauve a member
     */
    EvenementDto saveEvenement(EvenementDto evenementDto);

    /**
     * Get a member by it's id
     */
    EvenementDto getEvenementById(Long membreId);

    /**
     * Delete a member by it's id
     */
    ResponseEntity<?> deleteEvenement(Long membreId);

    /**
     * Get all the members
     */
    List<EvenementDto> getAllEvenements();

    /**
     * Add a member to an event
     */
    public ResponseEntity<?> addMembreToEvenement(Long evenementId, Membre membre);

    /**
     * update the location of an event
     */
    public EvenementDto updateLocation(Long evenementId, Long location);

    /**
     * delete the location of an event
     */

    public ResponseEntity<?> removeMembreFromEvenement(Long eventId, Long membreId);

    }
