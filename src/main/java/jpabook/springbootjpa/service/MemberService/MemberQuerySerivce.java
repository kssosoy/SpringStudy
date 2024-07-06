package jpabook.springbootjpa.service.MemberService;

import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.Review;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MemberQuerySerivce{
    Optional<Member> findMember(Long id);
    Page<Review> getReviewList(Long memberId, Integer page);
}
