package licenta.InsureMeRo.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "personal_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "person_type")
    private PersonType personType;
    @Column(name = "name")
    private String name;
    @Column(name = "identity_card_series")
    private String identityCardSeries;
    @Column(name = "identity_card_nr")
    private String identityCardNr;
    @Column(name = "code")
    private String code;
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Long addressId;
    @Column(name = "bonus_malus", columnDefinition = "varchar(255) default 'b0'")
    private String bonusMalus = "b0";


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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getBonusMalus() {
        return bonusMalus;
    }

    public void setBonusMalus(String bonusMalus) {
        this.bonusMalus = bonusMalus;
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
                ", address=" + addressId + //address +
                ", bonusMalus='" + bonusMalus + '\'' +
                '}';
    }
}
