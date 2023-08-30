package Repository;

import Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailorUsername(String email, String username);
    Optional<User> findByUsername(String username);
    Optional<User> findFirstByUsername(String username);
}
