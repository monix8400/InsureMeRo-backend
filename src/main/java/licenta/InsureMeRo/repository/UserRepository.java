package licenta.InsureMeRo.repository;

import licenta.InsureMeRo.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User SET firstname = ?1 where id=?2")
    void updateFirstname(String firstname, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE User SET lastname = ?1 where id=?2")
    void updateLastname(String lastname, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE User SET email = ?1 where id=?2")
    void updateEmail(String email, Long id);
}