package com.example.swt_kwt_aleksandar_simikic.Repository;

import com.example.swt_kwt_aleksandar_simikic.Model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    List<UserGroup> findByUserId(Long userId);
    List<UserGroup> findByGroupId(Long id);

    Optional<UserGroup> findByUserIdAndGroupId(Long userId, Long groupId);
    boolean existsByUserIdAndGroupId(Long userId, Long groupId);
}
