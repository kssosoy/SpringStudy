package jpabook.springbootjpa.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jpabook.springbootjpa.apiPayload.code.status.ErrorStatus;
import jpabook.springbootjpa.domain.Member;
//import jpabook.springbootjpa.validation.annotation.ExistMember;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class MemberExistsValidator implements ConstraintValidator<ExistMember, Long> {
//
//    private final MemberQueryService memberQueryService;
//
//    @Override
//    public void initialize(ExistMember constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }
//
//    @Override
//    public boolean isValid(Long value, ConstraintValidatorContext context) {
//        Optional<Member> target = memberQueryService.findMember(value);
//
//        if (target.isEmpty()){
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString()).addConstraintViolation();
//            return false;
//        }
//        return true;
//    }
//}