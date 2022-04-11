package licenta.InsureMeRo.Repository;

import licenta.InsureMeRo.Models.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
}