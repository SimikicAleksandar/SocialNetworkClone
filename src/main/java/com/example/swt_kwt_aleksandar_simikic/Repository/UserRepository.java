package com.example.swt_kwt_aleksandar_simikic.Repository;

import com.example.swt_kwt_aleksandar_simikic.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailOrUsername(String email, String username);
    Optional<User> findByUsername(String username);
    Optional<User> findFirstByUsername(String username);
}
