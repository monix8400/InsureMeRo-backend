package licenta.InsureMeRo.Repository;

import licenta.InsureMeRo.Models.InsuranceSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceSettingsRepository extends JpaRepository<InsuranceSettings, Long> {
}
