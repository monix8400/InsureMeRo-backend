package licenta.InsureMeRo.Models;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "insurances")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_info_id")
    private PersonalInfo personalInfo;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @OneToMany(mappedBy = "insurance")
    private List<Driver> drivers = new ArrayList<>();

    public Insurance() {
    }

    public Insurance(long id, Date issueDate, Date validFrom, Date validTo, float price, PersonalInfo personalInfo, Vehicle vehicle, User user, List<Driver> drivers) {
        this.id = id;
        this.issueDate = issueDate;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.price = price;
        this.personalInfo = personalInfo;
        this.vehicle = vehicle;
        this.user = user;
        this.drivers = drivers;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", issueDate=" + issueDate +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", price=" + price +
                ", personalInfo=" + personalInfo +
                ", vehicle=" + vehicle +
                ", user=" + user +
                ", drivers=" + drivers +
                '}';
    }
}
