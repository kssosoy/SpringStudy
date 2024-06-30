package jpabook.springbootjpa.domain.mapping;

import jakarta.persistence.*;
import jpabook.springbootjpa.domain.FoodCategory;
import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.common.BaseEntity;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity

public class MemberPrefer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private FoodCategory foodCategory;

    public void setMember (Member member){
        if(this.member != null) //  이미 다른 member 와 연결되어 있을 경우 해제 하는 내용
            member.getMemberPreferList().remove(this);
        this.member=member;
        member.getMemberPreferList().add(this);
    }
    public void setFoodCategory(FoodCategory foodCategory){
        this.foodCategory = foodCategory;
    }

}
