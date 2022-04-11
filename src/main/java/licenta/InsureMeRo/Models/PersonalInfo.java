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
    @Column(name = "code") //cnp/cui
    private String code;

}
