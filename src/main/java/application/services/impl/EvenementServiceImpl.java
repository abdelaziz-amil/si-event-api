package application.services.impl;

import application.dtos.EvenementDto;
import application.entities.Evenement;
import application.entities.Inscription;
import application.entities.InscriptionId;
import application.entities.Membre;
import application.repositories.EvenementRepository;
import application.repositories.InscriptionRepository;
import application.repositories.MembreRepository;
import application.services.EvenementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("evenementsService")
public class EvenementServiceImpl implements EvenementService {

	  private final EvenementRepository evenementsRepository;
    private final InscriptionRepository inscriptionRepository;
    private final MembreRepository membreRepository;

    public EvenementServiceImpl(EvenementRepository evenementsRepository, InscriptionRepository inscriptionRepository, MembreRepository membreRepository){
        this.evenementsRepository = evenementsRepository;
        this.inscriptionRepository = inscriptionRepository;
        this.membreRepository = membreRepository;
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
    public ResponseEntity<?> deleteEvenement(Long evenementId) {
        if(!evenementsRepository.existsById(evenementId)) return new ResponseEntity<>(Collections.singletonMap("message", "Événement non trouvé"),
                HttpStatus.NOT_FOUND);
        inscriptionRepository.deleteByEvenementId(evenementId);
        evenementsRepository.deleteById(evenementId);
        return new ResponseEntity<>(Collections.singletonMap("message", "Événement supprimé"), HttpStatus.OK);
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
        evenementDto.setTitre(evenement.getTitre());
        evenementDto.setDescription(evenement.getDescription());
        evenementDto.setLieuId(evenement.getLieuId());
        evenementDto.setDateHeureFin(evenement.getDateHeureFin());
        evenementDto.setDateHeureDebut(evenement.getDateHeureDebut());
        return evenementDto;
    }

    /**
     * Map member entity to member dto
     */
    private Evenement evenementDtoToEntity(EvenementDto evenementDto){
        Evenement evenement = new Evenement();
        evenement.setId(evenementDto.getId());
        evenement.setTitre(evenementDto.getTitre());
        evenement.setDescription(evenementDto.getDescription());
        evenement.setLieuId(evenementDto.getLieuId());
        evenement.setDateHeureFin(evenementDto.getDateHeureFin());
        evenement.setDateHeureDebut(evenementDto.getDateHeureDebut());
        return evenement;
    }

    /**
     * Get all the members in an event
     * @param evenementId The id of the event
     * @return ResponseEntity<?>
     */
    public ResponseEntity<?> getEvenementMembres(Long evenementId){

        List<Long> membresIds = inscriptionRepository.findMembreIdsByEvenementId(evenementId);

        List<Membre> membres = membreRepository.findAllById(membresIds);

        if (!membres.isEmpty()) {
            return new ResponseEntity<>(membres, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("message", "Il n'y a pas de membres inscrit à l'événement"),
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add a member to an event or Replace the member if it already exists by Id
     * @param evenementId The id of the event
     * @param membre The member to add
     * @return ResponseEntity<?>
     */
    @Override
    public ResponseEntity<?> addMembreToEvenement(Long evenementId, Membre membre) {
        if (!evenementsRepository.existsById(evenementId)) return new ResponseEntity<>(Collections.singletonMap("message", "L'évènement spécifié n'a pas été trouvé"),
                HttpStatus.NOT_FOUND);

        Inscription inscription = new Inscription();
        InscriptionId inscriptionId = new InscriptionId();
        inscriptionId.setIdMembre(membre.getId());
        inscriptionId.setIdEvenement(evenementId);
        inscription.setId(inscriptionId);

        inscription.setDateHeureInscription(LocalDateTime.now());

        // Enregistrer l'Inscription dans la base de données
        inscriptionRepository.save(inscription);
        return new ResponseEntity<>(membre, HttpStatus.OK);
    }
    @Override
    public EvenementDto updateLocation(Long evenementId, Long location){
        Evenement evenement = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        evenement.setLieuId(location);
        evenement = evenementsRepository.save(evenement);
        return evenementEntityToDto(evenement);
    }

    @Override
    public ResponseEntity<?> removeMembreFromEvenement(Long eventId, Long membreId) {
        if (!evenementsRepository.existsById(eventId)) return new ResponseEntity<>(Collections.singletonMap("message", "L'évènement spécifié n'a pas été trouvé"),
                HttpStatus.NOT_FOUND);
        InscriptionId inscriptionId = new InscriptionId();
        inscriptionId.setIdEvenement(eventId);
        inscriptionId.setIdMembre(membreId);
        inscriptionRepository.deleteById(inscriptionId);
        return new ResponseEntity<>(Collections.singletonMap("message", "Membre retiré de l'événement"), HttpStatus.OK);
    }
}
