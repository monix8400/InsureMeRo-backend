package licenta.InsureMeRo.Controllers;

import licenta.InsureMeRo.Models.Insurance;
import licenta.InsureMeRo.Services.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")//(origins = "http://localhost:4200")
@RequestMapping("/insurance")
public class InsuranceController {
    private final InsuranceService insuranceService;

    // standard constructors, dependency injection
    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping("/getInsurances")
    public List<Insurance> getInsurances() {
        return insuranceService.getInsurances();
    }

    @PostMapping("/addInsurance")
    public void addInsurance(@RequestBody Insurance insurance) {
        insuranceService.addInsurance(insurance);
    }

    @GetMapping("/getInsuranceById/{id}")
    public Insurance getInsuranceById(@PathVariable("id") Long id) {
        return insuranceService.getInsuranceById(id).orElse(null);
    }

    @DeleteMapping("/deleteInsuranceById/{id}")
    public void deleteInsuranceById(@PathVariable("id") Long id) {
        insuranceService.deleteInsurance(id);
    }

    //update

}
