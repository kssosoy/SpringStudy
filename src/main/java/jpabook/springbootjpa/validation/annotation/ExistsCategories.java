package jpabook.springbootjpa.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jpabook.springbootjpa.validation.validator.CategoriesExistValidator;

import java.lang.annotation.*;

@Documented //사용자 정의 어노테이션 만들 때
@Constraint(validatedBy = CategoriesExistValidator.class)
//해당 클래스를 통해 대상을 검증함
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
//어노테이션의 적용 범위 지정
@Retention(RetentionPolicy.RUNTIME)
//어노테이션의 생명주기 지정 (위의 경우는 실행하는 동안만 유효)
public @interface ExistsCategories {
    String message() default "해당하는 카테고리가 존재하지 않습니다";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
