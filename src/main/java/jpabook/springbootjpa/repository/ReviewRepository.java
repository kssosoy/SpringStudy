package jpabook.springbootjpa.repository;

import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Page<Review> findAllByMember (Member member, PageRequest pageRequest);
}
