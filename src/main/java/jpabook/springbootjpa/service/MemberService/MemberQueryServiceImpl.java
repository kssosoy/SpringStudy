package jpabook.springbootjpa.service.MemberService;

import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.Mission;
import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.domain.mapping.MemberMission;
import jpabook.springbootjpa.repository.MemberMissionRepository;
import jpabook.springbootjpa.repository.MemberRepository;
import jpabook.springbootjpa.repository.MissionRepository;
import jpabook.springbootjpa.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQuerySerivce{
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long memberId, Integer page){
        Member member = memberRepository.findById(memberId).get();
        Page<Review> MemberPage = reviewRepository.findAllByMember(member, PageRequest.of(page,10));
        return MemberPage;
    }

    @Override
    public Page<MemberMission> getIngMissionList(Long memberId, Integer page){
        Member member = memberRepository.findById(memberId).get();
        Page<MemberMission> MemberPage = memberMissionRepository.findAllByMember(member, PageRequest.of(page,10));
        return MemberPage;
    }
}
