package jpabook.springbootjpa.converter;

import jpabook.springbootjpa.domain.enums.MissionStatus;
import jpabook.springbootjpa.domain.mapping.MemberMission;
import jpabook.springbootjpa.web.dto.MemberRequestDTO;
import jpabook.springbootjpa.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {
    public static MemberResponseDTO.challengeMissionDTO tochallengeMissionDTO(MemberMission memberMission){
        return MemberResponseDTO.challengeMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static MemberMission toMembermission (MemberRequestDTO.MemberMissionDto request){
        MissionStatus missionStatus = null;
        switch(request.getStatus()){
            case 1:
                missionStatus = MissionStatus.SUCCESS;
                break;
            case 2:
                missionStatus = MissionStatus.ING;
            case 3:
                missionStatus =MissionStatus.NOTYET;
        }
        return MemberMission.builder()
                .status(missionStatus)
                .build();
    }
}
