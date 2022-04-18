package licenta.InsureMeRo.Services;

import licenta.InsureMeRo.Models.Insurance;
import licenta.InsureMeRo.Repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    public void addInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);
    }

    public List<Insurance> getInsurances() {
        return insuranceRepository.findAll();
    }

    public Optional<Insurance> getInsuranceById(Long id) {
        return insuranceRepository.findById(id);
    }

    public void deleteInsurance(Long id) {
        insuranceRepository.deleteById(id);
    }

    //update
}
