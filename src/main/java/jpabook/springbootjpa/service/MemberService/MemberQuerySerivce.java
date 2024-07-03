package jpabook.springbootjpa.service.MemberService;

import jpabook.springbootjpa.domain.Member;

import java.util.Optional;

public interface MemberQuerySerivce{
    Optional<Member> findMember(Long id);
}
