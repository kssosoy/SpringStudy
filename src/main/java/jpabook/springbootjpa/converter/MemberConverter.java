package jpabook.springbootjpa.converter;

import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.Mission;
import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.domain.enums.Gender;
import jpabook.springbootjpa.domain.enums.MissionStatus;
import jpabook.springbootjpa.domain.mapping.MemberMission;
import jpabook.springbootjpa.web.dto.MemberRequestDTO;
import jpabook.springbootjpa.web.dto.MemberResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MemberConverter {
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember (MemberRequestDTO.JoinDto request){
        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;

        }
        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }
    public static MemberResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return MemberResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static MemberResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<MemberResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(MemberConverter::reviewPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();

    }
    public static MemberResponseDTO.IngMissionListDTO ingMissionListDTO(Page<MemberMission> ingmissionList) {
        List<MemberResponseDTO.IngMissionDTO> ingMissionDTOList = ingmissionList.stream()
                .filter(memberMission -> memberMission.getStatus() == MissionStatus.ING)
                .map(MemberConverter::ingMissionDTO)
                .filter(Objects::nonNull) // null 값 필터링
                .collect(Collectors.toList());

        return MemberResponseDTO.IngMissionListDTO.builder()
                .isLast(ingmissionList.isLast())
                .isFirst(ingmissionList.isFirst())
                .totalPage(ingmissionList.getTotalPages())
                .totalElements(ingmissionList.getTotalElements())
                .listSize(ingMissionDTOList.size())
                .missionList(ingMissionDTOList)
                .build();
    }

    public static MemberResponseDTO.IngMissionDTO ingMissionDTO(MemberMission membermission) {
            return MemberResponseDTO.IngMissionDTO.builder()
                    .status(membermission.getStatus())
                    .deadline(membermission.getMission().getDeadline())
                    .missionSpec(membermission.getMission().getMissionSpec())
                    .reward(membermission.getMission().getReward())
                    .build();

    }
}
