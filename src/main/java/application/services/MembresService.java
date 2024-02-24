package application.services;

import application.dtos.MembresDto;

import java.util.List;

public interface MembresService {
    /**
     * Sauve a member
     */
    MembresDto saveMembre(MembresDto membresDto);

    /**
     * Get a member by it's id
     */
    MembresDto getMembreById(Long membreId);

    /**
     * Delete a member by it's id
     */
    boolean deleteMembre(Long membreId);

    /**
     * Get all the members
     */
    List<MembresDto> getAllMembres();


}
