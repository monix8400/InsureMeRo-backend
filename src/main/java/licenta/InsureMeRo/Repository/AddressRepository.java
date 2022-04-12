package licenta.InsureMeRo.Repository;

import licenta.InsureMeRo.Models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}