package application.repositories;

import application.entities.Inscription;
import application.entities.InscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, InscriptionId> {
  @Modifying
  @Transactional
  @Query("DELETE FROM Inscription i WHERE i.id.idEvenement = :evenementId")
  void deleteByEvenementId(@Param("evenementId") Long evenementId);

  @Query("SELECT i.id.idMembre FROM Inscription i WHERE i.id.idEvenement = :evenementId")
  List<Long> findMembreIdsByEvenementId(@Param("evenementId") Long evenementId);

  @Query("SELECT i.id.idEvenement FROM Inscription i WHERE i.id.idMembre = :membreId")
  List<Long> findEvenementIdsByMembreId(@Param("membreId") Long membreId);
}
