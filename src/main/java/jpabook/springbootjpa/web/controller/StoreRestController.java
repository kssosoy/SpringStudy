package jpabook.springbootjpa.web.controller;

import jakarta.validation.Valid;
import jpabook.springbootjpa.apiPayload.ApiResponse;
import jpabook.springbootjpa.converter.StoreConverter;
import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.service.StoreService.StoreCommandService;
import jpabook.springbootjpa.web.dto.StoreRequestDTO;
import jpabook.springbootjpa.web.dto.StoreResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/{storeId}/review")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> join (@RequestBody @Valid StoreRequestDTO. ReviewDTO request,
                                                                    @PathVariable(name="storeId") Long storeId, @RequestParam(name="memberId") Long memberId)
    {
        Review review =storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }
}
