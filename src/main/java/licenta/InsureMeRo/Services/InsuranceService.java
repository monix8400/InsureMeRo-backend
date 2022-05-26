package licenta.InsureMeRo.Services;

import licenta.InsureMeRo.Models.*;
import licenta.InsureMeRo.Repository.InsuranceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final InsuranceSettingsService insuranceSettingsService;

    @Autowired
    public InsuranceService(InsuranceRepository insuranceRepository, InsuranceSettingsService insuranceSettingsService) {
        this.insuranceRepository = insuranceRepository;
        this.insuranceSettingsService = insuranceSettingsService;
    }

    public Insurance addInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);
        return insurance;
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

    public Driver convertPersonalInfoToDriver(Long insuranceId, String userName, PersonalInfo personalInfo) {
        Driver driver = new Driver();
        driver.setIdentityCardNr(personalInfo.getIdentityCardNr());
        driver.setIdentityCardSeries(personalInfo.getIdentityCardSeries());
        driver.setName(userName);
        driver.setPersonalIdentificationNumber(personalInfo.getCode());
        driver.setInsurance(insuranceId);
        return driver;
    }

    public float calculatePrice(int nrMonths, PersonalInfo personalInfo) {
        float ageFactor = nrMonths == 12 ? 0.7f : 0.8f;
        float standardPrice = insuranceSettingsService.getInsuranceSettingsById(1L).get().getStandardPrice();
        return standardPrice * nrMonths * ageFactor * getAge(personalInfo.getCode()) * getBonusMalusFromPersonalInfo(personalInfo);
    }

    private float getBonusMalusFromPersonalInfo(PersonalInfo personalInfo) {
        return switch (personalInfo.getBonusMalus()) {
            case "b1" -> insuranceSettingsService.getInsuranceSettingsById(1L).get().getBonus1();
            case "b2" -> insuranceSettingsService.getInsuranceSettingsById(1L).get().getBonus2();
            case "b3" -> insuranceSettingsService.getInsuranceSettingsById(1L).get().getBonus3();
            case "b4" -> insuranceSettingsService.getInsuranceSettingsById(1L).get().getBonus4();
            case "b5" -> insuranceSettingsService.getInsuranceSettingsById(1L).get().getBonus5();
            case "m1" -> insuranceSettingsService.getInsuranceSettingsById(1L).get().getMalus1();
            case "m2" -> insuranceSettingsService.getInsuranceSettingsById(1L).get().getMalus2();
            case "m3" -> insuranceSettingsService.getInsuranceSettingsById(1L).get().getMalus3();
            case "m4" -> insuranceSettingsService.getInsuranceSettingsById(1L).get().getMalus4();
            case "m5" -> insuranceSettingsService.getInsuranceSettingsById(1L).get().getMalus5();
            default -> 1;
        };
    }

    public float getAge(String code) {
        int age = getAgeFromCode(code);
        if (age <= 25) {
            return insuranceSettingsService.getInsuranceSettingsById(1L).get().getAgeBelow25();
        } else if (age <= 40) {
            return insuranceSettingsService.getInsuranceSettingsById(1L).get().getAgeBetween26and40();
        } else if (age <= 55) {
            return insuranceSettingsService.getInsuranceSettingsById(1L).get().getAgeBetween41and55();
        } else if (age <= 65) {
            return insuranceSettingsService.getInsuranceSettingsById(1L).get().getAgeBetween56and65();
        } else {
            return insuranceSettingsService.getInsuranceSettingsById(1L).get().getAgeAbove66();
        }
    }

    public int getAgeFromCode(String code) {
        var year = Integer.parseInt(code.substring(1, 3));
        var gender = Integer.parseInt(code.substring(0, 1));
        if (gender >= 5) {
            year = 2000 + year;
        } else {
            year = 1900 + year;
        }
        var month = Integer.parseInt(code.substring(3, 5));
        var day = Integer.parseInt(code.substring(5, 7));
        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();

        return Period.between(birthday, today).getYears();
    }

    public void updatePrice(Long id, float newPrice) {
        log.info("id: {}, newPrice: {}", id, newPrice);
        Insurance insurance = getInsuranceById(id).get();
        if (insurance != null) {
            insuranceRepository.updatePrice(newPrice, id);
        }
    }
}