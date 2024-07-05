package jpabook.springbootjpa.converter;

import jpabook.springbootjpa.domain.Mission;
import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.web.dto.StoreRequestDTO;
import jpabook.springbootjpa.web.dto.StoreResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {
    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){

        return StoreResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Review toReview (StoreRequestDTO.ReviewDTO request){
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }
    public static StoreResponseDTO.CreateMissionResultDTO createMissionResultDTO(Mission mission){
        return StoreResponseDTO.CreateMissionResultDTO.builder()
                .missonId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Mission toMission(StoreRequestDTO.MissionDTO request){
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();
    }
    public static StoreResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review){
        return StoreResponseDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDTO.ReviewPreviewListDTO reviewPreviewListDTO(Page<Review> reviewList){
        List<StoreResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(StoreConverter:: reviewPreviewDTO).collect(Collectors.toList());
        return StoreResponseDTO.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewDTOList.size())
                .reviewList(reviewPreviewDTOList)
                .build();
    }
}
