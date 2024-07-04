package jpabook.springbootjpa.web.controller;

import jakarta.validation.Valid;
import jpabook.springbootjpa.apiPayload.ApiResponse;
import jpabook.springbootjpa.converter.MemberConverter;
import jpabook.springbootjpa.converter.MemberMissionConverter;
import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.mapping.MemberMission;
import jpabook.springbootjpa.service.MemberService.MemberCommandService;
import jpabook.springbootjpa.web.dto.MemberRequestDTO;
import jpabook.springbootjpa.web.dto.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor //final 또는 @NonNull로 선언된 필드 인수로 가지는 생성자 생성
@RequestMapping("/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
    @PostMapping("/mission/{missionId}")
    public ApiResponse<MemberResponseDTO.challengeMissionDTO> join(@RequestBody @Valid MemberRequestDTO.MemberMissionDto request,
                                                                   @PathVariable(name="missionId")Long missionId,
                                                                   @RequestParam(name="memberId") Long memberId){
        MemberMission memberMission = memberCommandService.joinMemberMission(memberId, missionId,request);
        return ApiResponse.onSuccess(MemberMissionConverter.tochallengeMissionDTO(memberMission));
    }
}
