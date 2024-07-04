package jpabook.springbootjpa.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jpabook.springbootjpa.domain.enums.MissionStatus;
import jpabook.springbootjpa.validation.annotation.ExistsCategories;
import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {
    @Getter
    public static class JoinDto{
        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @ExistsCategories
        List<Long> preferCategory;

    }
    @Getter
    public static class MemberMissionDto {
        @NotNull
        Integer status;
    }
}
