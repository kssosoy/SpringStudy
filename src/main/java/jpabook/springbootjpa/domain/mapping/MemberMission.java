package jpabook.springbootjpa.domain.mapping;

import jakarta.persistence.*;
import jpabook.springbootjpa.domain.Member;
import jpabook.springbootjpa.domain.Mission;
import jpabook.springbootjpa.domain.common.BaseEntity;
import jpabook.springbootjpa.domain.enums.MissionStatus;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class MemberMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // 이 어노테이션을 통해 enum을 entity적용
    @Column(columnDefinition = "VARCHAR(10)")
    private MissionStatus status;

    @ManyToOne
    @JoinColumn(name ="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="mission_id")
    private Mission mission;

    public void setMember(Member member){
        this.member= member;
        member.getMemberMissionList().add(this);
    }

    public void setMission(Mission mission){
        this.mission =mission;
        mission.getMemberMissions().add(this);
    }


}
