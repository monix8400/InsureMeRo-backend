package licenta.InsureMeRo.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "insurances")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "issue_date")
    private Date issueDate;
    @Column(name = "valid_from")
    private Date validFrom;
    @Column(name = "valid_to")
    private Date validTo;
    @Column(name = "price")
    private float price;

    //    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_info_id")
    private Long personalInfoId;//PersonalInfo personalInfo;

    //    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Long vehicleId;

    //    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Long userId;//User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

//    public PersonalInfo getPersonalInfo() {
//        return personalInfo;
//    }
//
//    public void setPersonalInfo(PersonalInfo personalInfo) {
//        this.personalInfo = personalInfo;
//    }
//
//    public Vehicle getVehicle() {
//        return vehicle;
//    }
//
//    public void setVehicle(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    public Long getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(Long personalInfoId) {
        this.personalInfoId = personalInfoId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", issueDate=" + issueDate +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", price=" + price +
                ", personalInfo=" + personalInfoId +
                ", vehicle=" + vehicleId +
                ", user=" + userId +
                '}';
    }
}
