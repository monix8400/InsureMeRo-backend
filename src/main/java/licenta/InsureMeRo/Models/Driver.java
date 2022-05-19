package licenta.InsureMeRo.Models;


import javax.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="personal_identification_number")
    private String personalIdentificationNumber;
    @Column(name="identity_card_series")
    private String identityCardSeries;
    @Column(name="identity_card_nr")
    private String identityCardNr;

    private Long insuranceId;

    public Driver() {
    }

    public Driver(String name, String personalIdentificationNumber, String identityCardSeries, String identityCardNr, Long insuranceId) {
        this.name = name;
        this.personalIdentificationNumber = personalIdentificationNumber;
        this.identityCardSeries = identityCardSeries;
        this.identityCardNr = identityCardNr;
        this.insuranceId = insuranceId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalIdentificationNumber() {
        return personalIdentificationNumber;
    }

    public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
        this.personalIdentificationNumber = personalIdentificationNumber;
    }

    public String getIdentityCardSeries() {
        return identityCardSeries;
    }

    public void setIdentityCardSeries(String identityCardSeries) {
        this.identityCardSeries = identityCardSeries;
    }

    public String getIdentityCardNr() {
        return identityCardNr;
    }

    public void setIdentityCardNr(String identityCardNr) {
        this.identityCardNr = identityCardNr;
    }

    public Long getInsurance() {
        return insuranceId;
    }

    public void setInsurance(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personalIdentificationNumber='" + personalIdentificationNumber + '\'' +
                ", identityCardSeries='" + identityCardSeries + '\'' +
                ", identityCardNr='" + identityCardNr + '\'' +
                ", insuranceId=" + insuranceId +
                '}';
    }
}
