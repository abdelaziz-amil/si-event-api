package application.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "inscription")
public class Inscription {
    @EmbeddedId
    private InscriptionId id;
    @Column(name = "dateHeureInscription")
    private LocalDateTime dateHeureInscription;
}
