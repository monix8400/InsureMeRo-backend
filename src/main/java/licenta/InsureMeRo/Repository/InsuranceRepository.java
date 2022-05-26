package licenta.InsureMeRo.Repository;

import licenta.InsureMeRo.Models.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Insurance SET price = ?1 where id=?2")
    void updatePrice(float newPrice,Long id);
}