package application.services.impl;

import application.dtos.EvenementsDto;
import application.entities.Evenements;
import application.entities.Membres;
import application.repositories.EvenementsRepository;
import application.repositories.MembresRepository;
import application.services.EvenementsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("evenementsService")
public class EvenementsServiceImpl implements EvenementsService {

	private final EvenementsRepository evenementsRepository;

    public EvenementsServiceImpl(EvenementsRepository evenementsRepository){
        this.evenementsRepository = evenementsRepository;
    }

    @Override
    public EvenementsDto saveEvenement(EvenementsDto evenementsDto) {
        // Converts the dto to the evenements entity
        Evenements evenements = evenementDtoToEntity(evenementsDto);
        // Save the evenements entity
        evenements = evenementsRepository.save(evenements);
        // Return the new dto
        return evenementEntityToDto(evenements);
    }

    @Override
    public EvenementsDto getEvenementById(Long evenementId) {
        Evenements evenements = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        return evenementEntityToDto(evenements);
    }

    @Override
    public boolean deleteEvenement(Long evenementId) {
        //TODO : desinscrire les membres de l'evenement
        evenementsRepository.deleteById(evenementId);
        return true;
    }

    @Override
    public List<EvenementsDto> getAllEvenements() {
        List<EvenementsDto> evenementsDtos = new ArrayList<>();
        List<Evenements> evenements = evenementsRepository.findAll();
        evenements.forEach(evenement -> {
            evenementsDtos.add(evenementEntityToDto(evenement));
        });
        return evenementsDtos;
    }

    /**
     * Map evenements dto to evenements entity
     */
    private EvenementsDto evenementEntityToDto(Evenements evenements){
        EvenementsDto evenementsDto = new EvenementsDto();
        evenementsDto.setId(evenements.getId());
        evenementsDto.setTitle(evenements.getTitle());
        evenementsDto.setDescription(evenements.getDescription());
        evenementsDto.setLocationId(evenements.getLocationId());
        evenementsDto.setEndTime(evenements.getEndTime());
        evenementsDto.setStartTime(evenements.getStartTime());
        evenementsDto.setMembres(evenements.getMembres());
        return evenementsDto;
    }

    /**
     * Map member entity to member dto
     */
    private Evenements evenementDtoToEntity(EvenementsDto evenementsDto){
        Evenements evenements = new Evenements();
        evenements.setId(evenementsDto.getId());
        evenements.setTitle(evenementsDto.getTitle());
        evenements.setDescription(evenementsDto.getDescription());
        evenements.setLocationId(evenementsDto.getLocationId());
        evenements.setEndTime(evenementsDto.getEndTime());
        evenements.setStartTime(evenementsDto.getStartTime());
        evenements.setMembres(evenementsDto.getMembres());
        return evenements;
    }

    /**
     * Get all the members in an event
     * @param evenementId The id of the event
     * @return List<Membres>
     */
    public List<Membres> getEvenementMembres(Long evenementId){
        Evenements evenements = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        return evenements.getMembres();
    }

    /**
     * Add a member to an event or Replace the member if it already exists by Id
     * @param evenementId The id of the event
     * @param membres The member to add
     * @return EvenementsDto
     */
    public EvenementsDto addMembreToEvenement(Long evenementId, Membres membres) {
        Evenements evenements = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        evenements.getMembres().removeIf(m -> m.getId().equals(membres.getId()));
        evenements.getMembres().add(membres);
        evenements = evenementsRepository.save(evenements);
        return evenementEntityToDto(evenements);
    }

    public EvenementsDto updateLocation(Long evenementId, Long location){
        Evenements evenements = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        evenements.setLocationId(location);
        evenements = evenementsRepository.save(evenements);
        return evenementEntityToDto(evenements);
    }

    public EvenementsDto deleteLocation(Long evenementId){
        Evenements evenements = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        evenements.setLocationId(null);
        evenements = evenementsRepository.save(evenements);
        return evenementEntityToDto(evenements);
    }
}
