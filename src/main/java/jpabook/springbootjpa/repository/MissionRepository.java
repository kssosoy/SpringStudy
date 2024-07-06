package jpabook.springbootjpa.repository;

import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.Mission;
import jpabook.springbootjpa.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByStore (Store store, PageRequest pageRequest);


}
