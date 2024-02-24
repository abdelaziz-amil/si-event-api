package application.services.impl;

import application.dtos.MembresDto;
import application.entities.Membres;
import application.repositories.MembresRepository;
import application.services.MembresService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("membresService")
public class MembresServiceImpl implements MembresService {

	private final MembresRepository membresRepository;

    public MembresServiceImpl(MembresRepository membresRepository){
        this.membresRepository = membresRepository;
    }

    @Override
    public MembresDto saveMembre(MembresDto membresDto) {
        // Converts the dto to the membres entity
        Membres membres = membreDtoToEntity(membresDto);
        // Save the membres entity
        membres = membresRepository.save(membres);
        // Return the new dto
        return membreEntityToDto(membres);
    }

    @Override
    public MembresDto getMembreById(Long membreId) {
        Membres membres = membresRepository.findById(membreId).orElseThrow(() -> new EntityNotFoundException("Membres not found"));
        return membreEntityToDto(membres);
    }

    @Override
    public boolean deleteMembre(Long membreId) {
        membresRepository.deleteById(membreId);
        return true;
    }

    @Override
    public List<MembresDto> getAllMembres() {
        List<MembresDto> membresDtos = new ArrayList<>();
        List<Membres> membres = membresRepository.findAll();
        membres.forEach(membre -> {
            membresDtos.add(membreEntityToDto(membre));
        });
        return membresDtos;
    }

    /**
     * Map membres dto to membres entity
     */
    private MembresDto membreEntityToDto(Membres membres){
        MembresDto membresDto = new MembresDto();
        membresDto.setId(membres.getId());
        membresDto.setNom(membres.getNom());
        membresDto.setPrenom(membres.getPrenom());
        membresDto.setDateNaissance(membres.getDateNaissance());
        membresDto.setAdresse(membres.getAdresse());
        membresDto.setMail(membres.getMail());
        membresDto.setMotDePasse(membres.getMotDePasse());
        return membresDto;
    }

    /**
     * Map member entity to member dto
     */
    private Membres membreDtoToEntity(MembresDto membresDto){
        Membres membres = new Membres();
        membres.setId(membresDto.getId());
        membres.setNom(membresDto.getNom());
        membres.setPrenom(membresDto.getPrenom());
        membres.setDateNaissance(membresDto.getDateNaissance());
        membres.setMail(membresDto.getMail());
        membres.setAdresse(membresDto.getAdresse());
        membres.setMotDePasse(membresDto.getMotDePasse());
        return membres;
    }
}
