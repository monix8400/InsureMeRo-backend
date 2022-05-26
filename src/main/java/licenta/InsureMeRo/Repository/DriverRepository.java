package licenta.InsureMeRo.Repository;

import licenta.InsureMeRo.Models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}