package licenta.InsureMeRo.Repository;

import licenta.InsureMeRo.Models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}