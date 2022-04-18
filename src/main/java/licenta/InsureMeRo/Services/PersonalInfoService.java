package licenta.InsureMeRo.Services;

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

    public void addPersonalInfo(PersonalInfo personalInfo) {
        personalInfoRepository.save(personalInfo);
    }

    public List<PersonalInfo> getPersonalInfo() {
        return personalInfoRepository.findAll();
    }

    public Optional<PersonalInfo> getPersonalInfoById(Long id) {
        return personalInfoRepository.findById(id);
    }

    public void deletePersonalInfo(Long id) {
        personalInfoRepository.deleteById(id);
    }

    //update
}
