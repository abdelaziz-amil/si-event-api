package application.services.impl;

import application.dtos.EvenementDto;
import application.dtos.EvenementPostDto;
import application.dtos.MembreInscriptionDto;
import application.entities.Evenement;
import application.entities.Inscription;
import application.entities.InscriptionId;
import application.entities.Membre;
import application.repositories.EvenementRepository;
import application.repositories.InscriptionRepository;
import application.repositories.MembreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("evenementsService")
public class EvenementServiceImpl {

    private final EvenementRepository evenementsRepository;
    private final InscriptionRepository inscriptionRepository;
    private final MembreRepository membreRepository;

    public EvenementServiceImpl(EvenementRepository evenementsRepository, InscriptionRepository inscriptionRepository, MembreRepository membreRepository){
        this.evenementsRepository = evenementsRepository;
        this.inscriptionRepository = inscriptionRepository;
        this.membreRepository = membreRepository;
    }

    public EvenementDto saveEvenement(EvenementPostDto evenementDto) {
        Evenement evenement = evenementDtoToEntity(evenementDto);
        evenement = evenementsRepository.save(evenement);
        return evenementEntityToDto(evenement);
    }

    public EvenementDto getEvenementById(Long evenementId) {
        Evenement evenement = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        return evenementEntityToDto(evenement);
    }

    public ResponseEntity<?> deleteEvenement(Long evenementId) {
        if(!evenementsRepository.existsById(evenementId)) return new ResponseEntity<>(Collections.singletonMap("message", "Événement non trouvé"),
                HttpStatus.NOT_FOUND);
        inscriptionRepository.deleteByEvenementId(evenementId);
        evenementsRepository.deleteById(evenementId);
        return new ResponseEntity<>(Collections.singletonMap("message", "Événement supprimé"), HttpStatus.OK);
    }

    public List<EvenementDto> getAllEvenements() {
        List<EvenementDto> evenementDtos = new ArrayList<>();
        List<Evenement> evenements = evenementsRepository.findAll();
        evenements.forEach(evenement -> {
            evenementDtos.add(evenementEntityToDto(evenement));
        });
        return evenementDtos;
    }

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

    private Evenement evenementDtoToEntity(EvenementPostDto evenementDto){
        Evenement evenement = new Evenement();
        evenement.setTitre(evenementDto.getTitre());
        evenement.setDescription(evenementDto.getDescription());
        evenement.setLieuId(evenementDto.getLieuId());
        evenement.setDateHeureFin(evenementDto.getDateHeureFin());
        evenement.setDateHeureDebut(evenementDto.getDateHeureDebut());
        return evenement;
    }

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

    public ResponseEntity<?> addMembreToEvenement(Long evenementId, MembreInscriptionDto membre) {
        if (!evenementsRepository.existsById(evenementId)) return new ResponseEntity<>(Collections.singletonMap("message", "L'évènement spécifié n'a pas été trouvé"),
                HttpStatus.NOT_FOUND);
        if (!membreRepository.existsById(membre.getId())) return new ResponseEntity<>(Collections.singletonMap("message", "Membre non trouvé"),
                HttpStatus.NOT_FOUND);

        // Vérification du chevauchement d'événement
        Evenement evenementAverifier = evenementsRepository.findById(evenementId)
                .orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));

        List<Long> evenementsIds = inscriptionRepository.findEvenementIdsByMembreId(membre.getId());
        List<Evenement> evenements = evenementsRepository.findAllById(evenementsIds);

        for (Evenement evenement : evenements) {
            boolean debutChevauche = evenement.getDateHeureDebut().isBefore(evenementAverifier.getDateHeureFin())
                    && evenement.getDateHeureDebut().isAfter(evenementAverifier.getDateHeureDebut());
            boolean finChevauche = evenement.getDateHeureFin().isBefore(evenementAverifier.getDateHeureFin())
                    && evenement.getDateHeureFin().isAfter(evenementAverifier.getDateHeureDebut());
            boolean inclusDans = evenement.getDateHeureDebut().isBefore(evenementAverifier.getDateHeureDebut())
                    && evenement.getDateHeureFin().isAfter(evenementAverifier.getDateHeureFin());

            if (debutChevauche || finChevauche || inclusDans) {
                return new ResponseEntity<>(Collections.singletonMap("error", "Le membre est déjà inscrit à un événement qui se déroule au même moment"),
                        HttpStatus.BAD_REQUEST);
            }
        }


        Inscription inscription = new Inscription();
        InscriptionId inscriptionId = new InscriptionId();
        inscriptionId.setIdMembre(membre.getId());
        inscriptionId.setIdEvenement(evenementId);
        inscription.setId(inscriptionId);
        inscription.setDateHeureInscription(LocalDateTime.now());

        inscriptionRepository.save(inscription);
        return new ResponseEntity<>(membre, HttpStatus.OK);
    }

    public EvenementDto updateEvenement(Long evenementId, EvenementPostDto evenementDto) {
        Evenement evenement = evenementsRepository.findById(evenementId)
                .orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        evenement.setTitre(evenementDto.getTitre());
        evenement.setDescription(evenementDto.getDescription());
        evenement.setLieuId(evenementDto.getLieuId());
        evenement.setDateHeureFin(evenementDto.getDateHeureFin());
        evenement.setDateHeureDebut(evenementDto.getDateHeureDebut());
        evenement = evenementsRepository.save(evenement);
        return evenementEntityToDto(evenement);
    }

    public EvenementDto updateLocation(Long evenementId, Long location){
        Evenement evenement = evenementsRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("Événement non trouvé"));
        evenement.setLieuId(location);
        evenement = evenementsRepository.save(evenement);
        return evenementEntityToDto(evenement);
    }

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
