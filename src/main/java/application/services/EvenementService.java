package application.services;

import application.dtos.EvenementDto;

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
    boolean deleteEvenement(Long membreId);

    /**
     * Get all the members
     */
    List<EvenementDto> getAllEvenements();


}
