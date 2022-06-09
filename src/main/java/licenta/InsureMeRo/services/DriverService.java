package licenta.InsureMeRo.services;

import licenta.InsureMeRo.models.Driver;
import licenta.InsureMeRo.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void addDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

}
