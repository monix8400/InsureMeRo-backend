package licenta.InsureMeRo.repository;

import licenta.InsureMeRo.models.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentsRepository extends JpaRepository<Incident, Long> {
}
