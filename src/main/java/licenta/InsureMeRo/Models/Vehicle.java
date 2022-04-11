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
    private String Make;
    @Column(name="model")
    private String Model;
    @Column(name="year_of_manufacture")
    private Date YearOfManufacture;
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
}
