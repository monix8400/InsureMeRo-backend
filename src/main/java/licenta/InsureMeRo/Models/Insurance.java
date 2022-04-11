package licenta.InsureMeRo.Models;

import javax.persistence.*;
import java.sql.Date;

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

}
