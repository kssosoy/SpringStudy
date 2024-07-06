package jpabook.springbootjpa.service.MemberService;

import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.Mission;
import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.domain.mapping.MemberMission;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MemberQuerySerivce{
    Optional<Member> findMember(Long id);
    Page<Review> getReviewList(Long memberId, Integer page);

    Page<MemberMission> getIngMissionList(Long memberId, Integer page);
}
