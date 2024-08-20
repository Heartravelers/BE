package heartraveler.server.domain.review.controller;

import heartraveler.server.domain.reservation.dto.ReservationListResponse;
import heartraveler.server.domain.reservation.dto.ReservationPreviewResponse;
import heartraveler.server.domain.review.service.PlaceReviewQueryService;
import heartraveler.server.domain.user.exception.UserExceptionHandler;
import heartraveler.server.global.common.ApiResponse;
import heartraveler.server.global.common.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place-review")
@Tag(name = "Place-Review", description = "플레이스 리뷰 관련 API")
@RequiredArgsConstructor
public class PlaceReviewController {
    private final PlaceReviewQueryService placeReviewQueryService;

    @Operation(summary = "최근 예약한 장소 3군데 불러오기  ")
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<ReservationPreviewResponse>>> getRecentReservations(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(ApiResponse.onSuccess(placeReviewQueryService.getRecentReservations(userId)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.onFailure(HttpStatus.BAD_REQUEST.name(), e.getMessage()));
        } catch (UserExceptionHandler e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.onFailure(ErrorCode.USER_NOT_FOUND.getCode(), ErrorCode.USER_NOT_FOUND.getMessage()));
        }
    }

    @Operation(summary = "플레이스 리뷰 작성할 예약 내역 검색")
    @GetMapping("/search/{userId}")
    public ResponseEntity<ApiResponse<ReservationListResponse>> getReservationList (@PathVariable Long userId,
                                                                                    @RequestParam String keyword,
                                                                                    @RequestParam(required = false) Long cursor,
                                                                                    @RequestParam(defaultValue = "10") int pageSize){
        try {
            return ResponseEntity.ok(ApiResponse.onSuccess(placeReviewQueryService.getReservationList(userId, keyword, cursor, pageSize)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.onFailure(HttpStatus.BAD_REQUEST.name(), e.getMessage()));
        } catch (UserExceptionHandler e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.onFailure(ErrorCode.USER_NOT_FOUND.getCode(), ErrorCode.USER_NOT_FOUND.getMessage()));
        }
    }

}
