package licenta.InsureMeRo.dto;

import licenta.InsureMeRo.Models.Address;
import licenta.InsureMeRo.Models.PersonalInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoDTO {

    Address address;
    PersonalInfo personalInfo;

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
}
