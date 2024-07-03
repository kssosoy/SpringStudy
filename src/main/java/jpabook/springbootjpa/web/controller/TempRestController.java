package jpabook.springbootjpa.web.controller;

import jpabook.springbootjpa.apiPayload.ApiResponse;
import jpabook.springbootjpa.converter.TempConverter;
import jpabook.springbootjpa.service.TempService.TempQueryService;
import jpabook.springbootjpa.web.dto.TempResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //RESTful 웹 서비스의 컨트롤러임을 나타냄, HTTP 요청 처리 JSON 응답 생성
@RequestMapping("/temp")
@RequiredArgsConstructor //final 필드 @NonNUll 붙은 필드를 포함하는 생성자를 자동으로 생성, 의존성 주입 필요 x

public class TempRestController {
    private final TempQueryService tempQueryService;
    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI(){

        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }
    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        tempQueryService.CheckFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));

    }
}