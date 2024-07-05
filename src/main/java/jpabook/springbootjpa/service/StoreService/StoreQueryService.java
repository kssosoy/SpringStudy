package jpabook.springbootjpa.service.StoreService;

import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.domain.Store;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    Page<Review> getReviewList (Long StoreId, Integer page);
}
