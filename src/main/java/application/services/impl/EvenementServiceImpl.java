package application.services.impl;

import application.dtos.EvenementDto;
import application.entities.Evenement;
import application.entities.Membre;
import application.repositories.EvenementRepository;
import application.services.EvenementService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("evenementsService")
public class EvenementServiceImpl implements EvenementService {

	private final EvenementRepository evenementsRepository;

    public EvenementServiceImpl(EvenementRepository evenementsRepository){
        this.evenementsRepository = evenementsRepository;
    }

    @Override
    public EvenementDto saveEvenement(EvenementDto evenementDto) {
        // Converts the dto to the evenement entity
        Evenement evenement = evenementDtoToEntity(evenementDto);
        // Save the evenement entity
        evenement = evenementsRepository.save(evenement);
        // Return the new dto
        return evenementEntityToDto(evenement);
    }

    @Override
    public EvenementDto getEvenementById(Long evenementId) {
        Evenement evenement = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        return evenementEntityToDto(evenement);
    }

    @Override
    public boolean deleteEvenement(Long evenementId) {
        //TODO : desinscrire les membres de l'evenement
        evenementsRepository.deleteById(evenementId);
        return true;
    }

    @Override
    public List<EvenementDto> getAllEvenements() {
        List<EvenementDto> evenementDtos = new ArrayList<>();
        List<Evenement> evenements = evenementsRepository.findAll();
        evenements.forEach(evenement -> {
            evenementDtos.add(evenementEntityToDto(evenement));
        });
        return evenementDtos;
    }

    /**
     * Map evenement dto to evenement entity
     */
    private EvenementDto evenementEntityToDto(Evenement evenement){
        EvenementDto evenementDto = new EvenementDto();
        evenementDto.setId(evenement.getId());
        evenementDto.setTitle(evenement.getTitle());
        evenementDto.setDescription(evenement.getDescription());
        evenementDto.setLocationId(evenement.getLocationId());
        evenementDto.setEndTime(evenement.getEndTime());
        evenementDto.setStartTime(evenement.getStartTime());
        evenementDto.setMembres(evenement.getMembres());
        return evenementDto;
    }

    /**
     * Map member entity to member dto
     */
    private Evenement evenementDtoToEntity(EvenementDto evenementDto){
        Evenement evenement = new Evenement();
        evenement.setId(evenementDto.getId());
        evenement.setTitle(evenementDto.getTitle());
        evenement.setDescription(evenementDto.getDescription());
        evenement.setLocationId(evenementDto.getLocationId());
        evenement.setEndTime(evenementDto.getEndTime());
        evenement.setStartTime(evenementDto.getStartTime());
        evenement.setMembres(evenementDto.getMembres());
        return evenement;
    }

    /**
     * Get all the members in an event
     * @param evenementId The id of the event
     * @return List<Membre>
     */
    public List<Membre> getEvenementMembres(Long evenementId){
        Evenement evenement = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        return evenement.getMembres();
    }

    /**
     * Add a member to an event or Replace the member if it already exists by Id
     * @param evenementId The id of the event
     * @param membre The member to add
     * @return EvenementDto
     */
    public EvenementDto addMembreToEvenement(Long evenementId, Membre membre) {
        Evenement evenement = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        evenement.getMembres().removeIf(m -> m.getId().equals(membre.getId()));
        evenement.getMembres().add(membre);
        evenement = evenementsRepository.save(evenement);
        return evenementEntityToDto(evenement);
    }

    public EvenementDto updateLocation(Long evenementId, Long location){
        Evenement evenement = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        evenement.setLocationId(location);
        evenement = evenementsRepository.save(evenement);
        return evenementEntityToDto(evenement);
    }

    public EvenementDto deleteLocation(Long evenementId){
        Evenement evenement = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        evenement.setLocationId(null);
        evenement = evenementsRepository.save(evenement);
        return evenementEntityToDto(evenement);
    }
}
