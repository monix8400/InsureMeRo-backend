package licenta.InsureMeRo.services;

import licenta.InsureMeRo.models.PersonType;
import licenta.InsureMeRo.models.PersonalInfo;
import licenta.InsureMeRo.repository.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInfoService {

    private final PersonalInfoRepository personalInfoRepository;

    @Autowired
    public PersonalInfoService(PersonalInfoRepository personalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
    }

    public PersonalInfo addPersonalInfo(PersonalInfo personalInfo) {
        var actualPersonalInfo = getPersonalInfo().stream()
                .filter(pi -> pi.getCode().equals(personalInfo.getCode()))
                .findFirst();

        if (actualPersonalInfo.isEmpty()) {
            return personalInfoRepository.save(personalInfo); //personalInfo;
        } else {
            personalInfo.setId(actualPersonalInfo.get().getId());
            updateAll(personalInfo);
            return personalInfo;
        }
//        return personalInfo;
    }

    public List<PersonalInfo> getPersonalInfo() {
        return personalInfoRepository.findAll();
    }

    public Optional<PersonalInfo> getPersonalInfoById(Long id) {
        return personalInfoRepository.findById(id);
    }

    public Optional<PersonalInfo> getPersonalInfoByCode(String Code) {
        return personalInfoRepository.findByCode(Code).stream().findFirst();
    }

    public void deletePersonalInfo(Long id) {
        personalInfoRepository.deleteById(id);
    }

    public void update(PersonalInfo personalInfo) {
        PersonType p = personalInfo.getPersonType();
        String n = personalInfo.getName();
        String ics = personalInfo.getIdentityCardSeries();
        String icn = personalInfo.getIdentityCardNr();
        Long a = personalInfo.getAddressId();
        String bm = personalInfo.getBonusMalus();
        String c = personalInfo.getCode();
        personalInfoRepository.update(p, n, ics, icn, a, bm, c);
    }

    public void updateAll(PersonalInfo personalInfo) {
        PersonType p = personalInfo.getPersonType();
        String n = personalInfo.getName();
        String ics = personalInfo.getIdentityCardSeries();
        String icn = personalInfo.getIdentityCardNr();
        Long id = personalInfo.getId();
        Long a = personalInfo.getAddressId();
        String bm = personalInfo.getBonusMalus();
        String c = personalInfo.getCode();
        personalInfoRepository.updateAll(p, n, ics, icn, id, a, bm, c);
    }

    public boolean checkIfPersonalInfoCodeExists(String code) {
        var actualPersonalInfo = getPersonalInfo().stream()
                .filter(pi -> pi.getCode().equals(code))
                .findFirst();

        return actualPersonalInfo.isPresent();
    }

    public void updateBonus(PersonalInfo personalInfo, int nrIncidents, int nrInsurances) {
        int currentBonus = getBonusStringToInt(personalInfo.getBonusMalus());
        if (currentBonus >= 0) {
            if (nrIncidents >= 2) {
                personalInfoRepository.updateBonus("b0", personalInfo.getCode());
            } else if (nrIncidents == 1) {
                currentBonus = currentBonus - 1;
                personalInfoRepository.updateBonus(getBonusIntToString(currentBonus), personalInfo.getCode());
            } else {
                currentBonus = currentBonus + nrInsurances;
                if (currentBonus > 5) currentBonus = 5;
                personalInfoRepository.updateBonus(getBonusIntToString(currentBonus), personalInfo.getCode());
            }
        } else {
            if (nrIncidents > 0) {
                currentBonus = currentBonus - 1;
                if (currentBonus < -5) currentBonus = -5;
                personalInfoRepository.updateBonus(getBonusIntToString(currentBonus), personalInfo.getCode());
            } else {
                currentBonus = currentBonus + nrInsurances;
                if (currentBonus > 5) currentBonus = 5;
                personalInfoRepository.updateBonus(getBonusIntToString(currentBonus), personalInfo.getCode());
            }
        }
    }

    public String getBonusIntToString(int bonus) {
        return switch (bonus) {
            case 5 -> "b5";
            case 4 -> "b4";
            case 3 -> "b3";
            case 2 -> "b2";
            case 1 -> "b1";
            case 0 -> "b0";
            case -1 -> "m1";
            case -2 -> "m2";
            case -3 -> "m3";
            case -4 -> "m4";
            case -5 -> "m5";
            default -> null;
        };
    }

    public int getBonusStringToInt(String bonus) {
        if (bonus.startsWith("b")) {
            if (bonus.endsWith("0")) {
                return 0;
            } else if (bonus.endsWith("1")) {
                return 1;
            } else if (bonus.endsWith("2")) {
                return 2;
            } else if (bonus.endsWith("3")) {
                return 3;
            } else if (bonus.endsWith("4")) {
                return 4;
            } else if (bonus.endsWith("5")) {
                return 5;
            }
        } else {
            if (bonus.endsWith("1")) {
                return -1;
            } else if (bonus.endsWith("2")) {
                return -2;
            } else if (bonus.endsWith("3")) {
                return -3;
            } else if (bonus.endsWith("4")) {
                return -4;
            } else if (bonus.endsWith("5")) {
                return -5;
            }
        }
        return 0;
    }

}

