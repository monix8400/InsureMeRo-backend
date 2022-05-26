package licenta.InsureMeRo.Services;

import licenta.InsureMeRo.Models.PersonType;
import licenta.InsureMeRo.Models.PersonalInfo;
import licenta.InsureMeRo.Repository.PersonalInfoRepository;
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

    public boolean checkIfPersonalInfoCodeExists(String code){
        var actualPersonalInfo = getPersonalInfo().stream()
                .filter(pi -> pi.getCode().equals(code))
                .findFirst();

        return actualPersonalInfo.isPresent();
    }
}
