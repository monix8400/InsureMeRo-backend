package licenta.InsureMeRo.dto;

import licenta.InsureMeRo.Models.Driver;
import licenta.InsureMeRo.Models.PersonalInfo;
import licenta.InsureMeRo.Models.Vehicle;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDTO {
    PersonalInfo personalInfo;
    Vehicle vehicle;
    List<Driver> driverList;

    Date startDate;

    int Months;

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

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getMonths() {
        return Months;
    }

    public void setMonths(int months) {
        Months = months;
    }
}
