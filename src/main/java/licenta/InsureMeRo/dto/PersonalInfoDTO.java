package licenta.InsureMeRo.dto;

import licenta.InsureMeRo.models.Address;
import licenta.InsureMeRo.models.PersonalInfo;
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
