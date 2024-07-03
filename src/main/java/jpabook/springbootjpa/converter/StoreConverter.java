package jpabook.springbootjpa.converter;

import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.web.dto.StoreRequestDTO;
import jpabook.springbootjpa.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

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
}
