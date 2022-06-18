package licenta.InsureMeRo.controllers;

import licenta.InsureMeRo.dto.*;
import licenta.InsureMeRo.models.*;
import licenta.InsureMeRo.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
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
    private final IncidentsService incidentsService;

    @Autowired
    public InsuranceController(InsuranceService insuranceService, PersonalInfoService personalInfoService, AddressService addressService, VehicleService vehicleService, DriverService driverService, UserService userService, IncidentsService incidentsService) {
        this.insuranceService = insuranceService;
        this.personalInfoService = personalInfoService;
        this.addressService = addressService;
        this.vehicleService = vehicleService;
        this.driverService = driverService;
        this.userService = userService;
        this.incidentsService = incidentsService;
    }

    @PostMapping("/addInsurance")
    public ResponseEntity<Long> addInsurance(@RequestBody InsuranceInfoDTO insuranceInfoDTO) {

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

        Address address = addressService.addAddress(insuranceInfoDTO.getAddress());
        insuranceInfoDTO.getPersonalInfo().setAddressId(address.getId());
        PersonalInfo personalInfo = personalInfoService.addPersonalInfo(insuranceInfoDTO.getPersonalInfo());
//        updateBonus(personalInfo.getId());
        insurance.setPersonalInfoId(personalInfo.getId());

        insuranceService.addInsurance(insurance);

        Long insuranceId = insurance.getId();
        Driver userToDriver = insuranceService.convertPersonalInfoToDriver(insuranceId, insuranceInfoDTO.getPersonalInfo());
        driverService.addDriver(userToDriver);
        for (Driver driver : insuranceInfoDTO.getDriverList()) {
            driver.setInsurance(insuranceId);
            driverService.addDriver(driver);
        }

        if (insuranceInfoDTO.getPersonalInfo().getPersonType() == PersonType.INDIVIDUAL) {
            float newPrice = insuranceService.calculatePrice(insuranceInfoDTO.getMonths(), personalInfo);
            insuranceService.updatePrice(insuranceId, newPrice);
        }
        return ResponseEntity.ok().body(insuranceId);
    }

    @GetMapping("/getInsurances")
    public List<InsuranceDTO> getInsurances() {
        List<Insurance> insurancesList = insuranceService.getInsurances();
        List<InsuranceDTO> insuranceDTOList = new ArrayList<>();
        for (Insurance insurance : insurancesList) {
            InsuranceDTO insuranceDTO = insuranceToInsuranceDTO(insurance);
            insuranceDTOList.add(insuranceDTO);
        }
        return insuranceDTOList;
    }

    @GetMapping("/getInsuranceById/{id}")
    public Insurance getInsuranceById(@PathVariable("id") Long id) {
        return insuranceService.getInsuranceById(id).orElse(null);
    }

    @GetMapping("/getInsurancesForCurrentUser")
    public List<InsuranceDTO> getInsurancesForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail((String) auth.getPrincipal()).get();

        List<Insurance> insurancesList = insuranceService.getInsurances();
        List<InsuranceDTO> insuranceDTOList = new ArrayList<>();

        for (Insurance insurance : insurancesList) {
            if (insurance.getUserId() == user.getId()) {
                InsuranceDTO insuranceDTO = insuranceToInsuranceDTO(insurance);
                insuranceDTOList.add(insuranceDTO);
            }
        }
        return insuranceDTOList;
    }

    @GetMapping(value = "/getInsurancePdf/{id}")
    public ResponseEntity<byte[]> getInsuranceAsPdf(@PathVariable("id") Long id) {
        try {
            HtmlToPdf htmlToPdf = new HtmlToPdf();
            Insurance insurance = getInsuranceById(id);
            InsuranceDTO insuranceDTO = insuranceToInsuranceDTO(insurance);
            var pdfFile = htmlToPdf.generateHtmlToPdf(insuranceDTO);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            return ResponseEntity.ok().headers(headers).body(pdfFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getPersonalInfoForCurrentUser/{id}")
    public List<PersonalInfoDTO> getPersonalInfoForCurrentUser(@PathVariable("id") Long id) {
        PersonType personType;
        if (id == 0) {
            personType = PersonType.INDIVIDUAL;
        } else {
            personType = PersonType.LEGAL_PERSON;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail((String) auth.getPrincipal()).get();

        List<Insurance> insurancesList = insuranceService.getInsurances();
        Map<String, PersonalInfoDTO> personalInfoDTOMap = new HashMap<>();

        for (Insurance insurance : insurancesList) {
            if (insurance.getUserId() == user.getId()) {
                PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO();

                PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(insurance.getPersonalInfoId()).get();
                if (personalInfo.getPersonType().equals(personType)) {
                    personalInfoDTO.setPersonalInfo(personalInfo);

                    Address address = addressService.getAddressById(personalInfo.getAddressId()).get();
                    personalInfoDTO.setAddress(address);

                    personalInfoDTOMap.put(personalInfoDTO.getPersonalInfo().getCode(), personalInfoDTO);
                }
            }
        }
        log.info(String.valueOf(personalInfoDTOMap.size()));
        List<PersonalInfoDTO> finalPersonalInfoList = new ArrayList<>();
        for (Map.Entry<String, PersonalInfoDTO> entry : personalInfoDTOMap.entrySet()) {
            finalPersonalInfoList.add(entry.getValue());
        }
        return finalPersonalInfoList;
    }

    @DeleteMapping("/deleteInsuranceById/{id}")
    public void deleteInsuranceById(@PathVariable("id") Long id) {
        insuranceService.deleteInsurance(id);
    }

    @GetMapping("getInsurancePrices")
    public ChartData getInsurancePrices() {
        List<InsuranceDTO> insuranceDTOList = getInsurances();

        ChartData chartData = new ChartData();
        ChartValues chartValues0 = new ChartValues(averagePricesProMonth(insuranceDTOList, PersonType.INDIVIDUAL), "Individual");
        chartData.chartValuesList.add(chartValues0);
        ChartValues chartValues1 = new ChartValues(averagePricesProMonth(insuranceDTOList, PersonType.LEGAL_PERSON), "Legal Person");
        chartData.chartValuesList.add(chartValues1);

        chartData.labels = getLastMonths(12).stream().map(localDate -> localDate.getMonth().getDisplayName(TextStyle.FULL,Locale.ENGLISH)).toList();
        return chartData;
    }

    private List<Double> averagePricesProMonth(List<InsuranceDTO> insuranceDTOList, PersonType personType) {
        List<InsuranceDTO> filteredInsuranceList = insuranceDTOList.stream()
                .filter(insurance -> insurance.getPersonalInfo().getPersonType() == personType)
                .toList();

        List<Double> average = new ArrayList<>();

        List<LocalDate> last12Months = getLastMonths(12);
        for (var month : last12Months) {

            List<Float> pricesPerMonth = filteredInsuranceList.stream()
                    .filter(insurance -> insurance.getInsurance().getValidFrom().toLocalDate().getMonth() == month.getMonth()
                            && insurance.getInsurance().getValidFrom().toLocalDate().getYear() == month.getYear())
                    .map(insurance -> insurance.getInsurance().getPrice())
                    .toList();

            var avg = pricesPerMonth.stream().mapToDouble(price -> (double) price).average().orElse(0.0);

            avg = Double.parseDouble(new DecimalFormat("0.00").format(avg));
            average.add(avg);
        }

        return average;
    }

    private List<LocalDate> getLastMonths(int noMonths) {
        List<LocalDate> lastMonths = new ArrayList<>();
        for (int i = noMonths-1; i >= 0; i--) {
            lastMonths.add(LocalDate.now().minusMonths(i));
        }
        return lastMonths;
    }

    private InsuranceDTO insuranceToInsuranceDTO(Insurance insurance) {
        InsuranceDTO insuranceDTO = new InsuranceDTO();
        insuranceDTO.setInsurance(insurance);
        PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(insurance.getPersonalInfoId()).get();
        insuranceDTO.setPersonalInfo(personalInfo);
        Vehicle vehicle = vehicleService.getVehicleById(insurance.getVehicleId()).get();
        insuranceDTO.setVehicle(vehicle);
        Address address = addressService.getAddressById(personalInfo.getAddressId()).get();
        insuranceDTO.setAddress(address);
        return insuranceDTO;
    }

    @PostMapping("/bonus/{id}")
    public void updateBonus(@PathVariable("id") Long id) {
        PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(id).get();
        List<Incident> incidentList = incidentsService.getIncidentsByPersonalInfoId(id);
        int nrInsurances = insuranceService.getNrInsurancesForGivenPersonalInfoId(id);
        personalInfoService.updateBonus(personalInfo, incidentList.size(), nrInsurances);
    }

}
