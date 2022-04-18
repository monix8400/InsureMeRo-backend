package licenta.InsureMeRo.Controllers;

import licenta.InsureMeRo.Models.PersonalInfo;
import licenta.InsureMeRo.Services.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")//(origins = "http://localhost:4200")
@RequestMapping("/personalInfo")
public class PersonalInfoController {
    private final PersonalInfoService personalInfoService;

    // standard constructors, dependency injection
    @Autowired
    public PersonalInfoController(PersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/getPersonalInfo")
    public List<PersonalInfo> getUsers() {
        return personalInfoService.getPersonalInfo();
    }

    @PostMapping("/addPersonalInfo")
    public void addPersonalInfo(@RequestBody PersonalInfo personalInfo) {
        personalInfoService.addPersonalInfo(personalInfo);
    }

    @GetMapping("/getPersonalInfoById/{id}")
    public PersonalInfo getPersonalInfoById(@PathVariable("id") Long id) {
        return personalInfoService.getPersonalInfoById(id).orElse(null);
    }

    @DeleteMapping("/deletePersonalInfoById/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        personalInfoService.deletePersonalInfo(id);
    }

    //update

}




