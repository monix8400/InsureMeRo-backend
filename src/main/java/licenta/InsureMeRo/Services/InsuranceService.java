package licenta.InsureMeRo.Services;

import licenta.InsureMeRo.Models.Insurance;
import licenta.InsureMeRo.Models.User;
import licenta.InsureMeRo.Repository.InsuranceRepository;
import licenta.InsureMeRo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final UserRepository userRepository;

    @Autowired
    public InsuranceService(InsuranceRepository insuranceRepository, UserRepository userRepository) {
        this.insuranceRepository = insuranceRepository;
        this.userRepository = userRepository;
    }

    public void addInsurance(Insurance insurance) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail((String) auth.getPrincipal()).get();
        insurance.setUser(user);

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
