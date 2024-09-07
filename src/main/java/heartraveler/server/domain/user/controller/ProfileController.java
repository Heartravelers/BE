package heartraveler.server.domain.user.controller;

import heartraveler.server.domain.user.dto.UpdateBadgeDto;
import heartraveler.server.domain.user.dto.UpdateIntroDto;
import heartraveler.server.domain.user.dto.UpdateNameDto;
import heartraveler.server.domain.user.entity.UserProfile;
import heartraveler.server.domain.user.repository.UserRepository;
import heartraveler.server.domain.user.service.ProfileService;
import heartraveler.server.global.common.ApiResponse;
import heartraveler.server.global.common.BaseResponseCode;
import heartraveler.server.global.common.ErrorCode;
import heartraveler.server.global.common.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name = "Profile", description = "마이페이지 프로필 관련 API")
public class ProfileController {

    @Autowired
    UserRepository userRepository; // 테스트용

    @Autowired
    ProfileService profileService;

    @Operation(summary = "닉네임 변경")
    @PutMapping("/mypage/profile/name")
    public ResponseEntity<ApiResponse<?>> updateName(@RequestBody UpdateNameDto updateNameDto) {
        try {
            Optional<UserProfile> testUserOptional = userRepository.findById(1L); // 테스트용
            if(testUserOptional.isPresent()) {
                UserProfile testUser = testUserOptional.get();
                BaseResponseCode response = profileService.updateName(testUser, updateNameDto.getName());
                if(response.getHttpStatus().equals(HttpStatus.OK)) {
                    return ResponseEntity.ok(ApiResponse.onSuccess((SuccessCode) response));
                }
                return ResponseEntity.ok(ApiResponse.onFailure(response.getCode(), response.getMessage()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorCode.USER_NOT_FOUND.getErrorResponse());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage()));
        }
    }

    @Operation(summary = "한 줄 소개 변경")
    @PutMapping("/mypage/profile/intro")
    public ResponseEntity<ApiResponse<?>> updateIntro(@RequestBody UpdateIntroDto updateIntroDto) {
        try {
            Optional<UserProfile> testUserOptional = userRepository.findById(1L); // 테스트용
            if(testUserOptional.isPresent()) {
                UserProfile testUser = testUserOptional.get();
                return ResponseEntity.ok(ApiResponse.onSuccess(profileService.updateIntro(testUser, updateIntroDto.getIntro())));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorCode.USER_NOT_FOUND.getErrorResponse());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage()));
        }
    }

    @Operation(summary = "뱃지 변경")
    @PutMapping("/mypage/profile/badge")
    public ResponseEntity<ApiResponse<?>> updateBadges(@RequestBody UpdateBadgeDto updateBadgeDto) {
        try {
            Optional<UserProfile> testUserOptional = userRepository.findById(1L); // 테스트용
            if(testUserOptional.isPresent()) {
                UserProfile testUser = testUserOptional.get();
                try {
                    return ResponseEntity.ok(ApiResponse.onSuccess(profileService.updateBadges(testUser, updateBadgeDto)));
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorCode.BADGE_BAD_REQUEST.getErrorResponse());
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorCode.USER_NOT_FOUND.getErrorResponse());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage()));
        }
    }
}
