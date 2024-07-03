package jpabook.springbootjpa.web.dto;

import lombok.*;

import java.time.LocalDateTime;

public class StoreResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResultDTO{
        Long reviewId;
        LocalDateTime createdAt;
    }

}
