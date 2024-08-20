package heartraveler.server.domain.review.controller;

import heartraveler.server.domain.review.dto.PostReviewRequest;
import heartraveler.server.domain.review.service.PostReviewCommandService;
import heartraveler.server.global.common.ApiResponse;
import heartraveler.server.global.common.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post/review")
@Tag(name = "Review", description = "포스트 리뷰 관련 API")
@RequiredArgsConstructor
public class PostReviewController {
    private final PostReviewCommandService postReviewCommandService;

    @Operation(summary = "리뷰 생성")
    @PostMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<SuccessCode>> createReview(@PathVariable Long userId,
                                                                 @Valid @RequestPart("postReviewRequest") PostReviewRequest postReviewRequest,
                                                                 @RequestPart(value = "reviewImages", required = false) List<MultipartFile> images) {
        try{
            return ResponseEntity.ok(ApiResponse.onSuccess(postReviewCommandService.createReview(postReviewRequest, images, userId)));
        }catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.onFailure(HttpStatus.BAD_REQUEST.name(), e.getMessage()));
        }
    }
}

