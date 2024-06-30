package jpabook.springbootjpa.domain;

import jakarta.persistence.*;
import jpabook.springbootjpa.domain.common.BaseEntity;
import jpabook.springbootjpa.domain.mapping.MemberAgree;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Terms extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    private boolean optional;

    @OneToMany(mappedBy="terms", cascade= CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();
}
