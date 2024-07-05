package jpabook.springbootjpa.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jpabook.springbootjpa.apiPayload.ApiResponse;
import jpabook.springbootjpa.converter.StoreConverter;
import jpabook.springbootjpa.domain.Mission;
import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.service.StoreService.StoreCommandService;
import jpabook.springbootjpa.service.StoreService.StoreQueryService;
import jpabook.springbootjpa.validation.annotation.ExistStore;
import jpabook.springbootjpa.web.dto.StoreRequestDTO;
import jpabook.springbootjpa.web.dto.StoreResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/{storeId}/review")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> join (@RequestBody @Valid StoreRequestDTO. ReviewDTO request,
                                                                    @PathVariable(name="storeId") Long storeId, @RequestParam(name="memberId") Long memberId)
    {
        Review review =storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }

    @PostMapping("/{storeId}/mission")
    public ApiResponse<StoreResponseDTO.CreateMissionResultDTO> join(@RequestBody @Valid StoreRequestDTO.MissionDTO request,
                                                                     @PathVariable(name="storeId") Long storeId){
        Mission mission = storeCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(StoreConverter.createMissionResultDTO(mission));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary="특정 가게의 리뷰 목록 조회 API",description="특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    //API에 대한 설명 작성, summary,description으로 설명을 적는다
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    //API의 응답을 담게되며 내부적으로 @ApiResponse로 각각의 응답들을 담게된다
    @Parameters({
            @Parameter(name="storeId",description="가게의 아이디, path variable 입니다!")
    })
    //프론트에서 넘겨줘야 할 정보를 담음
    public ApiResponse<StoreResponseDTO.ReviewPreviewListDTO> getReviewList(@ExistStore @PathVariable(name="storeId") Long storeId, @RequestParam(name="page") Integer page){
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreviewListDTO(reviewList));
    }
}
