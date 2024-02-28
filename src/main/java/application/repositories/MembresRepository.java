package application.repositories;

import application.entities.Evenements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembresRepository extends JpaRepository<Evenements, Long> {
}
