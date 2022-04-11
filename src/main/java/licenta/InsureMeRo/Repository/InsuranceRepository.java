package licenta.InsureMeRo.Repository;

import licenta.InsureMeRo.Models.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}