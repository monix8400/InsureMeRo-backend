package licenta.InsureMeRo.dto;

import licenta.InsureMeRo.Models.Address;
import licenta.InsureMeRo.Models.Insurance;
import licenta.InsureMeRo.Models.PersonalInfo;
import licenta.InsureMeRo.Models.Vehicle;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDTO {
    Address address;
    PersonalInfo personalInfo;
    Vehicle vehicle;
    Insurance insurance;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
