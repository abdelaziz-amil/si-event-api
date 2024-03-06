package application.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class InscriptionId implements Serializable {
  @Column(name = "membreId")
  private Long idMembre;
  @Column(name = "evenementId")
  private Long idEvenement;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InscriptionId that = (InscriptionId) o;
    return Objects.equals(idMembre, that.idMembre) && Objects.equals(idEvenement, that.idEvenement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idMembre, idEvenement);
  }

}
