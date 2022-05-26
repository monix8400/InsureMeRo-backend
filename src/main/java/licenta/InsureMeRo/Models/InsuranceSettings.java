package licenta.InsureMeRo.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "insurance_settings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceSettings{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "person_type")
    private PersonType personType;

    @Column(name = "standard_price")
    private float standardPrice;

    @Column(name = "bonus_1")
    private float bonus1;

    @Column(name = "bonus_2")
    private float bonus2;

    @Column(name = "bonus_3")
    private float bonus3;

    @Column(name = "bonus_4")
    private float bonus4;

    @Column(name = "bonus_5")
    private float bonus5;

    @Column(name = "malus_1")
    private float malus1;

    @Column(name = "malus_2")
    private float malus2;

    @Column(name = "malus_3")
    private float malus3;

    @Column(name = "malus_4")
    private float malus4;

    @Column(name = "malus_5")
    private float malus5;

    @Column(name = "age_below_25")
    private float ageBelow25;

    @Column(name = "age_between_26_and_40")
    private float ageBetween26and40;

    @Column(name = "age_between_41_and_55")
    private float ageBetween41and55;

    @Column(name = "age_between_56_and_65")
    private float ageBetween56and65;

    @Column(name = "age_above_66")
    private float ageAbove66;

    public PersonType getPersonType() {
        return personType;
    }

    public float getStandardPrice() {
        return standardPrice;
    }

    public float getBonus1() {
        return bonus1;
    }

    public float getBonus2() {
        return bonus2;
    }

    public float getBonus3() {
        return bonus3;
    }

    public float getBonus4() {
        return bonus4;
    }

    public float getBonus5() {
        return bonus5;
    }

    public float getMalus1() {
        return malus1;
    }

    public float getMalus2() {
        return malus2;
    }

    public float getMalus3() {
        return malus3;
    }

    public float getMalus4() {
        return malus4;
    }

    public float getMalus5() {
        return malus5;
    }

    public float getAgeBelow25() {
        return ageBelow25;
    }

    public float getAgeBetween26and40() {
        return ageBetween26and40;
    }

    public float getAgeBetween41and55() {
        return ageBetween41and55;
    }

    public float getAgeBetween56and65() {
        return ageBetween56and65;
    }

    public float getAgeAbove66() {
        return ageAbove66;
    }
}
