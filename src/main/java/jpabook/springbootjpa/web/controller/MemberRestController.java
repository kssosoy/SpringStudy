package jpabook.springbootjpa.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jpabook.springbootjpa.apiPayload.ApiResponse;
import jpabook.springbootjpa.converter.MemberConverter;
import jpabook.springbootjpa.converter.MemberMissionConverter;
import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.domain.mapping.MemberMission;
import jpabook.springbootjpa.service.MemberService.MemberCommandService;
import jpabook.springbootjpa.service.MemberService.MemberQuerySerivce;
import jpabook.springbootjpa.web.dto.MemberRequestDTO;
import jpabook.springbootjpa.web.dto.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor //final 또는 @NonNull로 선언된 필드 인수로 가지는 생성자 생성
@RequestMapping("/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final MemberQuerySerivce memberQuerySerivce;

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
    @GetMapping ("/{memberId}/reviews")
    @Operation(summary="내가 작성한 리뷰 목록 조회 API", description="특정 멤버가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호가 필요함")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name="memberId", description = "멤버의 아이디, path variable 입니다")
    })
    public ApiResponse<MemberResponseDTO.ReviewPreViewListDTO> getReviewList(@PathVariable(name="memberId") Long memberId, @RequestParam(name="page") Integer page){
        Page<Review> reviewList =memberQuerySerivce.getReviewList(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDTO(reviewList));
    }
}
