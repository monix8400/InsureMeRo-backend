package licenta.InsureMeRo.Models;


import javax.persistence.*;

@Entity
@Table(name = "personal_info")
public class PersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "person_type")
    private PersonType personType;
    @Column(name = "name")
    private String name;
    @Column(name="identity_card_series")
    private String identityCardSeries;
    @Column(name="identity_card_nr")
    private String identityCardNr;
    @Column(name = "code") //cnp/cui
    private String code;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;

    public PersonalInfo() {
    }

    public PersonalInfo(PersonType personType, String name, String identityCardSeries, String identityCardNr, String code, Address address) {
        this.personType = personType;
        this.name = name;
        this.identityCardSeries = identityCardSeries;
        this.identityCardNr = identityCardNr;
        this.code = code;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "id=" + id +
                ", personType=" + personType +
                ", name='" + name + '\'' +
                ", identityCardSeries='" + identityCardSeries + '\'' +
                ", identityCardNr='" + identityCardNr + '\'' +
                ", code='" + code + '\'' +
                ", address=" + address +
                '}';
    }
}
