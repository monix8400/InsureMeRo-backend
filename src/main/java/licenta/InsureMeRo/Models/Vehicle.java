package licenta.InsureMeRo.Models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="registration_nr")
    private String registrationNr;
    @Column(name="category_code")
    private String categoryCode;
    @Column(name="civseries")
    private String CIVSeries;
    @Column(name="make")
    private String make;
    @Column(name="model")
    private String model;
    @Column(name="year_of_manufacture")
    private Date yearOfManufacture;
    @Column(name="fuel_type")
    private String fuelType;
    @Column(name="cylindrical_capacity")
    private int cylindricalCapacity;
    @Column(name = "max_net_power")
    private int maxNetPower;
    @Column(name="max_total_mass")
    private int maxTotalMass;
    @Column(name="seats_nr")
    private int seatsNr;

    public Vehicle() {
    }

    public Vehicle(long id, String registrationNr, String categoryCode, String CIVSeries, String make, String model, Date yearOfManufacture, String fuelType, int cylindricalCapacity, int maxNetPower, int maxTotalMass, int seatsNr) {
        this.id = id;
        this.registrationNr = registrationNr;
        this.categoryCode = categoryCode;
        this.CIVSeries = CIVSeries;
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.fuelType = fuelType;
        this.cylindricalCapacity = cylindricalCapacity;
        this.maxNetPower = maxNetPower;
        this.maxTotalMass = maxTotalMass;
        this.seatsNr = seatsNr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistrationNr() {
        return registrationNr;
    }

    public void setRegistrationNr(String registrationNr) {
        this.registrationNr = registrationNr;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCIVSeries() {
        return CIVSeries;
    }

    public void setCIVSeries(String CIVSeries) {
        this.CIVSeries = CIVSeries;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Date yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getCylindricalCapacity() {
        return cylindricalCapacity;
    }

    public void setCylindricalCapacity(int cylindricalCapacity) {
        this.cylindricalCapacity = cylindricalCapacity;
    }

    public int getMaxNetPower() {
        return maxNetPower;
    }

    public void setMaxNetPower(int maxNetPower) {
        this.maxNetPower = maxNetPower;
    }

    public int getMaxTotalMass() {
        return maxTotalMass;
    }

    public void setMaxTotalMass(int maxTotalMass) {
        this.maxTotalMass = maxTotalMass;
    }

    public int getSeatsNr() {
        return seatsNr;
    }

    public void setSeatsNr(int seatsNr) {
        this.seatsNr = seatsNr;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", registrationNr='" + registrationNr + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", CIVSeries='" + CIVSeries + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", fuelType='" + fuelType + '\'' +
                ", cylindricalCapacity=" + cylindricalCapacity +
                ", maxNetPower=" + maxNetPower +
                ", maxTotalMass=" + maxTotalMass +
                ", seatsNr=" + seatsNr +
                '}';
    }
}
