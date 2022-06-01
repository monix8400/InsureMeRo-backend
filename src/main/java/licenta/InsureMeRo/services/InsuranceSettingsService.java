package licenta.InsureMeRo.services;

import licenta.InsureMeRo.models.InsuranceSettings;
import licenta.InsureMeRo.repository.InsuranceSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceSettingsService {

    private final InsuranceSettingsRepository insuranceSettingsRepository;

    @Autowired
    public InsuranceSettingsService(InsuranceSettingsRepository insuranceSettingsRepository) {
        this.insuranceSettingsRepository = insuranceSettingsRepository;
    }

    public List<InsuranceSettings> getInsurancesSettings() {
        return insuranceSettingsRepository.findAll();
    }

    public Optional<InsuranceSettings> getInsuranceSettingsById(Long id) {
        return insuranceSettingsRepository.findById(id);
    }
}
