package jpabook.springbootjpa.domain;

import jakarta.persistence.*;
import jpabook.springbootjpa.domain.common.BaseEntity;
import jpabook.springbootjpa.domain.enums.Gender;
import jpabook.springbootjpa.domain.enums.MemberStatus;
import jpabook.springbootjpa.domain.enums.SocialType;
import jpabook.springbootjpa.domain.mapping.MemberAgree;
import jpabook.springbootjpa.domain.mapping.MemberMission;
import jpabook.springbootjpa.domain.mapping.MemberPrefer;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=20)
    private String name;
    @Column(nullable=false, length=40)
    private String address;
    @Column(nullable=false, length=40)
    private String specAddress;

    @Enumerated(EnumType.STRING) // 이 어노테이션을 통해 enum을 entity적용
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    private LocalDate inactiveDate;
    //@Column(nullable = false, length =50)
    private String email;

    @ColumnDefault("0")
    private Integer point;

    @OneToMany(mappedBy = "member", cascade =CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy ="member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy ="member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade =CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();



}
