package application.services;

import application.dtos.EvenementsDto;

import java.util.List;

public interface EvenementsService {
    /**
     * Sauve a member
     */
    EvenementsDto saveEvenement(EvenementsDto evenementsDto);

    /**
     * Get a member by it's id
     */
    EvenementsDto getEvenementById(Long membreId);

    /**
     * Delete a member by it's id
     */
    boolean deleteEvenement(Long membreId);

    /**
     * Get all the members
     */
    List<EvenementsDto> getAllEvenements();


}
