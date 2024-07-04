package jpabook.springbootjpa.repository;

import jpabook.springbootjpa.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
