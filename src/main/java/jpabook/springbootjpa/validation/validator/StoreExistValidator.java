//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import jpabook.springbootjpa.apiPayload.code.status.ErrorStatus;
//import jpabook.springbootjpa.domain.Store;
//import jpabook.springbootjpa.service.StoreService.StoreQueryService;
//import jpabook.springbootjpa.validation.annotation.ExistStore;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {
//
//    private final StoreQueryService storeQueryService;
//
//    @Override
//    public void initialize(ExistStore constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }
//
//    @Override
//    public boolean isValid(Long value, ConstraintValidatorContext context) {
//        Optional<Store> target = storeQueryService.findStore(value);
//
//        if (target.isEmpty()){
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
//            return false;
//        }
//        return true;
//    }
//}
