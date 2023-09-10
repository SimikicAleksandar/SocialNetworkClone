package com.example.swt_kwt_aleksandar_simikic.Repository;

import com.example.swt_kwt_aleksandar_simikic.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
