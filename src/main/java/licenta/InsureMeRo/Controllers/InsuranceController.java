package licenta.InsureMeRo.Controllers;

import licenta.InsureMeRo.Models.Driver;
import licenta.InsureMeRo.Models.Insurance;
import licenta.InsureMeRo.Models.PersonalInfo;
import licenta.InsureMeRo.Models.User;
import licenta.InsureMeRo.Services.*;
import licenta.InsureMeRo.dto.InsuranceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@RestController
@CrossOrigin("*")//(origins = "http://localhost:4200")
@RequestMapping("/insurance")
@Slf4j
public class InsuranceController {
    private final InsuranceService insuranceService;
    private final PersonalInfoService personalInfoService;
    private final AddressService addressService;
    private final VehicleService vehicleService;
    private final DriverService driverService;

    private final UserService userService;

    // standard constructors, dependency injection
    @Autowired
    public InsuranceController(InsuranceService insuranceService, PersonalInfoService personalInfoService, AddressService addressService, VehicleService vehicleService, DriverService driverService, UserService userService) {
        this.insuranceService = insuranceService;
        this.personalInfoService = personalInfoService;
        this.addressService = addressService;
        this.vehicleService = vehicleService;
        this.driverService = driverService;
        this.userService = userService;
    }

    @GetMapping("/getInsurances")
    public List<Insurance> getInsurances() {
        return insuranceService.getInsurances();
    }

    @PostMapping("/addInsurance")
    public void addInsurance(@RequestBody InsuranceDTO insuranceDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail((String) auth.getPrincipal()).get();
        Insurance insurance = new Insurance();
        insurance.setUser(user);
        insurance.setIssueDate(Date.valueOf(LocalDate.now()));
        insurance.setValidFrom(insuranceDTO.getStartDate());
        if (insuranceDTO.getMonths() == 6) {

            LocalDate date = insuranceDTO.getStartDate().toLocalDate();
            LocalDate newDate = date.plusDays(180);

            insurance.setValidTo(Date.valueOf(newDate));
        } else {
            LocalDate date = insuranceDTO.getStartDate().toLocalDate();
            LocalDate newDate = date.plusDays(360);

            insurance.setValidTo(Date.valueOf(newDate));
        }
        insurance.setPersonalInfo(insuranceDTO.getPersonalInfo());
        insurance.setVehicle(insuranceDTO.getVehicle());
        insuranceService.addInsurance(insurance);
        log.info("added Insurance");

        Long insuranceId = insurance.getId();
        Driver userToDriver = convertPersonalInfoToDriver(insuranceId, auth.getName(), insuranceDTO.getPersonalInfo());
        driverService.addDriver(userToDriver);
        for (Driver driver : insuranceDTO.getDriverList()) {
            driver.setInsurance(insuranceId);
            driverService.addDriver(driver);
        }
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
