package application.repositories;

import application.entities.Evenements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementsRepository extends JpaRepository<Evenements, Long> {
}
