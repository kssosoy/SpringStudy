package jpabook.springbootjpa.domain;

import jakarta.persistence.*;
import jpabook.springbootjpa.domain.common.BaseEntity;
import jpabook.springbootjpa.domain.mapping.MemberMission;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer reward;

    private LocalDate deadline;

    private String missionSpec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    @OneToMany(mappedBy="mission", cascade=CascadeType.ALL)
    private List<MemberMission> memberMissions= new ArrayList<>();





}
