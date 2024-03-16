package application.services;

import application.dtos.EvenementDto;
import application.dtos.EvenementPostDto;
import application.dtos.MembreInscriptionDto;
import application.entities.Membre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EvenementService {

    /**
     * Save a new evenement
     * @param evenementDto the evenement to save
     * @return the saved evenement
     */
    EvenementDto saveEvenement(EvenementPostDto evenementDto);

    /**
     * Get an evenement by its id
     * @param membreId the id of the member
     * @return the evenement
     */
    EvenementDto getEvenementById(Long membreId);

    /**
     * Delete an evenement by its id
     * @param membreId the id of the evenement
     * @return a response entity
     */
    ResponseEntity<?> deleteEvenement(Long membreId);

    /**
     * Get all evenements
     * @return a list of all evenements available in database
     */
    List<EvenementDto> getAllEvenements();


    /**
     * Add a new membre to an evenement
     * @param evenementId the id of the evenement
     * @param membre the membre to add
     * @return a response entity indicating the success or not of the operation
     */
    ResponseEntity<?> addMembreToEvenement(Long evenementId, MembreInscriptionDto membre);

    /**
     * Get all membres of an evenement
     * @param evenementId the id of the evenement
     * @return a response entity indicating the success or not of the operation
     */
    ResponseEntity<?> getEvenementMembres(Long evenementId);

    /**
     * Update an evenement
     * @param evenementId the id of the evenement to update
     * @param evenementDto the new evenement data
     * @return the updated evenement
     */
    EvenementDto updateEvenement(Long evenementId, EvenementPostDto evenementDto);

    /**
     * Update the location of an evenement
     * @param evenementId the id of the evenement to update
     * @param location the new location
     * @return the updated evenement with the new location
     */
    public EvenementDto updateLocation(Long evenementId, Long location);

    /**
     * Remove a membre from an evenement
     * @param eventId the id of the evenement
     * @param membreId the id of the membre
     * @return a response entity with a message indicating the success or no of the operation
     */
    public ResponseEntity<?> removeMembreFromEvenement(Long eventId, Long membreId);

    }
