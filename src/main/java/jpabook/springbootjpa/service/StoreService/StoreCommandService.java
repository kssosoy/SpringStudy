package jpabook.springbootjpa.service.StoreService;

import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request);
}
