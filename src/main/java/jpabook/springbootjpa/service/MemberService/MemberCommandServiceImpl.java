package jpabook.springbootjpa.service.MemberService;

import jpabook.springbootjpa.apiPayload.code.status.ErrorStatus;
import jpabook.springbootjpa.apiPayload.exception.handler.FoodCategoryHandler;
import jpabook.springbootjpa.converter.MemberConverter;
import jpabook.springbootjpa.converter.MemberMissionConverter;
import jpabook.springbootjpa.converter.MemberPreferConverter;
import jpabook.springbootjpa.domain.FoodCategory;
import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.mapping.MemberMission;
import jpabook.springbootjpa.domain.mapping.MemberPrefer;
import jpabook.springbootjpa.repository.FoodCategoryRepository;
import jpabook.springbootjpa.repository.MemberMissionRepository;
import jpabook.springbootjpa.repository.MemberRepository;
import jpabook.springbootjpa.repository.MissionRepository;
import jpabook.springbootjpa.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Member joinMember (MemberRequestDTO.JoinDto request){
        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category ->{
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});
        return memberRepository.save(newMember);

    }

    @Override
    @Transactional
    public MemberMission joinMemberMission(Long MemberId, Long MissionId, MemberRequestDTO.MemberMissionDto request){
        MemberMission newMemberMission = MemberMissionConverter.toMembermission(request);
        newMemberMission.setMember(memberRepository.findById(MemberId).get());
        newMemberMission.setMission(missionRepository.findById(MissionId).get());
        return memberMissionRepository.save(newMemberMission);

    }


}
