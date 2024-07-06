package jpabook.springbootjpa.repository;

import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.Mission;
import jpabook.springbootjpa.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findAllByMember (Member member, PageRequest pageRequest);
}
