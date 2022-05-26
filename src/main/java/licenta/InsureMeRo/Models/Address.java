package licenta.InsureMeRo.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "county")
    private String county;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    @Column(name = "zip_code")
    private int zipCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", county='" + county + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", zipCode=" + zipCode +
                '}';
    }
}
