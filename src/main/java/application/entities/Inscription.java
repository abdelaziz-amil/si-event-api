package application.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
public class Inscription {
    @EmbeddedId
    private InscriptionId id;
    @Column(name = "dateHeureInscription")
    private LocalDateTime dateHeureInscription;
}
