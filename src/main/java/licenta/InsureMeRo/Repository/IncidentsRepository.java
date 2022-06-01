package licenta.InsureMeRo.Repository;

import licenta.InsureMeRo.Models.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentsRepository extends JpaRepository<Incident, Long> {
}
