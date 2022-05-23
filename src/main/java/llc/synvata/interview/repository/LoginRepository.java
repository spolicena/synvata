package llc.synvata.interview.repository;

import llc.synvata.interview.domain.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginRepository extends JpaRepository<LoginEntity, String> {
    @Query("select loginEntity FROM LoginEntity loginEntity " +
            "WHERE loginEntity.email = ?1 ")
    LoginEntity findByEmail(String email);
}
