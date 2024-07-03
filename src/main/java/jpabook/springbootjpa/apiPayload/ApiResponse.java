package jpabook.springbootjpa.apiPayload;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jpabook.springbootjpa.apiPayload.code.BaseCode;
import jpabook.springbootjpa.apiPayload.code.status.SuccessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 모든 필드에 대한 getter 메서드를 자동으로 생성
@JsonPropertyOrder({"isSuccess", "code", "message", "result"}) //JSON 직렬화 시 필드의 순서를 정의
public class ApiResponse<T> { //제네릭 타입 , 클래스나 메서드에서 사용할 데이터 타입을 나중에 정의

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result; // 제네릭 타입의 결과 데이터를 담는 필드 null이면 JSON 응답에 포함 안된다


     //성공한 경우 응답 생성

    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, SuccessStatus._OK.getCode() , SuccessStatus._OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result){
            return new ApiResponse<>(true, code.getReasonHttpStatus().getCode() , code.getReasonHttpStatus().getMessage(), result);
    }


    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(true, code, message, data);
    }
}