package jpabook.springbootjpa.service.MemberService;

import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQuerySerivce{
    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }
}
