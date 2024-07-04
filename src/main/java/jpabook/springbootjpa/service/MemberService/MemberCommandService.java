package jpabook.springbootjpa.service.MemberService;

import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.mapping.MemberMission;
import jpabook.springbootjpa.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
    MemberMission joinMemberMission(Long MemberId, Long MissionId, MemberRequestDTO.MemberMissionDto request);
}
