package licenta.InsureMeRo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "incidents")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "personal_info")
    private long personalInfoId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(long personalInfoId) {
        this.personalInfoId = personalInfoId;
    }
}
