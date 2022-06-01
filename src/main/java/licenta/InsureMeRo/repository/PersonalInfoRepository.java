package licenta.InsureMeRo.repository;

import licenta.InsureMeRo.models.PersonType;
import licenta.InsureMeRo.models.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
    Optional<PersonalInfo> findByCode(String Code);

    @Transactional
    @Modifying
    @Query("UPDATE PersonalInfo SET personType = ?1, name=?2, identityCardSeries=?3, identityCardNr=?4,id=?5 , addressId=?6, bonusMalus=?7 where code=?8")
    void updateAll(PersonType p, String n, String ics, String icn, Long id, Long addressId, String bm, String c);

    @Transactional
    @Modifying
    @Query("UPDATE PersonalInfo SET personType = ?1, name=?2, identityCardSeries=?3, identityCardNr=?4, addressId=?5, bonusMalus=?6 where code=?7")
    void update(PersonType p, String n, String ics, String icn, Long addressId, String bm, String c);

    @Transactional
    @Modifying
    @Query("UPDATE PersonalInfo SET bonusMalus=?1 where code=?2")
    void updateBonus(String bm, String c);
}