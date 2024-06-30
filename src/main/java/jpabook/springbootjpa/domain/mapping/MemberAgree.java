package jpabook.springbootjpa.domain.mapping;

import jakarta.persistence.*;
import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.Terms;
import jpabook.springbootjpa.domain.common.BaseEntity;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class MemberAgree extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="terms_id")
    private Terms terms;


}
