package jpabook.springbootjpa.domain;

import jakarta.persistence.*;
import jpabook.springbootjpa.domain.common.BaseEntity;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private Float score;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    public void setMember(Member member){
        if(this.member!= null)
            member.getReviewList().remove(this);
        this.member=member;
        member.getReviewList().add(this);
    }

    public void setStore(Store store){
        if(this.score != null)
            store.getReviewList().remove(this);
        this.store = store;
        store.getReviewList().add(this);
    }


}
