package licenta.InsureMeRo.Controllers;

import licenta.InsureMeRo.Models.*;
import licenta.InsureMeRo.Services.*;
import licenta.InsureMeRo.dto.InsuranceDTO;
import licenta.InsureMeRo.dto.InsuranceInfoDTO;
import licenta.InsureMeRo.dto.PersonalInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


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
    public List<InsuranceDTO> getInsurances() {
        List<Insurance> insurancesList = insuranceService.getInsurances();
        List<InsuranceDTO> insuranceDTOList = new ArrayList<>();
        for (Insurance insurance : insurancesList) {
            InsuranceDTO insuranceDTO = new InsuranceDTO();
            insuranceDTO.setInsurance(insurance);
            PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(insurance.getPersonalInfoId()).get();
            insuranceDTO.setPersonalInfo(personalInfo);
            Vehicle vehicle = vehicleService.getVehicleById(insurance.getVehicleId()).get();
            insuranceDTO.setVehicle(vehicle);

            insuranceDTOList.add(insuranceDTO);
        }
        return insuranceDTOList;
    }

    @GetMapping("/getInsurancesForCurrentUser")
    public List<InsuranceDTO> getInsurancesForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail((String) auth.getPrincipal()).get();

        List<Insurance> insurancesList = insuranceService.getInsurances();
        List<InsuranceDTO> insuranceDTOList = new ArrayList<>();

        for (Insurance insurance : insurancesList) {
            if (insurance.getUserId() == user.getId()) {
                InsuranceDTO insuranceDTO = new InsuranceDTO();
                insuranceDTO.setInsurance(insurance);
                PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(insurance.getPersonalInfoId()).get();
                insuranceDTO.setPersonalInfo(personalInfo);
                Vehicle vehicle = vehicleService.getVehicleById(insurance.getVehicleId()).get();
                insuranceDTO.setVehicle(vehicle);

                insuranceDTOList.add(insuranceDTO);
            }
        }
        return insuranceDTOList;
    }

    @GetMapping("/getPersonalInfoForCurrentUser")
    public Set<PersonalInfoDTO> getPersonalInfoForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail((String) auth.getPrincipal()).get();

        List<Insurance> insurancesList = insuranceService.getInsurances();
        Set<PersonalInfoDTO> personalInfoDTOSet = new HashSet<>();

        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO();

        for (Insurance insurance : insurancesList) {
            if (insurance.getUserId() == user.getId()) {

                PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(insurance.getPersonalInfoId()).get();
                personalInfoDTO.setPersonalInfo(personalInfo);

                Address address = addressService.getAddressById(personalInfo.getAddressId()).get();
                personalInfoDTO.setAddress(address);

                personalInfoDTOSet.add(personalInfoDTO);
            }
        }
        return personalInfoDTOSet;
    }

    @PostMapping("/addInsurance")
    public void addInsurance(@RequestBody InsuranceInfoDTO insuranceInfoDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail((String) auth.getPrincipal()).get();
        Insurance insurance = new Insurance();
        insurance.setUserId(user.getId());
        insurance.setIssueDate(Date.valueOf(LocalDate.now()));
        insurance.setValidFrom(insuranceInfoDTO.getStartDate());

        LocalDate date = insuranceInfoDTO.getStartDate().toLocalDate();
        LocalDate newDate = (insuranceInfoDTO.getMonths() == 6) ? date.plusDays(180) : date.plusDays(360);
        insurance.setValidTo(Date.valueOf(newDate));

        Vehicle vehicle = vehicleService.addVehicle(insuranceInfoDTO.getVehicle());
        insurance.setVehicleId(vehicle.getId());

        PersonalInfo personalInfo = personalInfoService.addPersonalInfo(insuranceInfoDTO.getPersonalInfo());
        insurance.setPersonalInfoId(personalInfo.getId());

        insuranceService.addInsurance(insurance);

        Long insuranceId = insurance.getId();
        Driver userToDriver = insuranceService.convertPersonalInfoToDriver(insuranceId, auth.getName(), insuranceInfoDTO.getPersonalInfo());
        driverService.addDriver(userToDriver);
        for (Driver driver : insuranceInfoDTO.getDriverList()) {
            driver.setInsurance(insuranceId);
            driverService.addDriver(driver);
        }

        if (insuranceInfoDTO.getPersonalInfo().getPersonType() == PersonType.INDIVIDUAL) {
            float newPrice = insuranceService.calculatePrice(insuranceInfoDTO.getMonths(), personalInfo);
            insuranceService.updatePrice(insuranceId, newPrice);
        }
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
