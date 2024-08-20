package heartraveler.server.domain.review.service;

import heartraveler.server.domain.review.dto.PostReviewRequest;
import heartraveler.server.domain.review.entity.PostReview;
import heartraveler.server.domain.review.entity.ReviewImg;
import heartraveler.server.domain.review.repository.PostReviewRepository;
import heartraveler.server.domain.user.entity.UserProfile;
import heartraveler.server.domain.user.exception.UserExceptionHandler;
import heartraveler.server.domain.user.repository.UserRepository;
import heartraveler.server.global.common.ErrorCode;
import heartraveler.server.global.common.SuccessCode;
import heartraveler.server.global.util.s3.S3Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReviewCommandService {
    private final PostReviewRepository postReviewRepository;
    private final UserRepository userRepository;
    private final S3Provider s3Provider;

    @Transactional
    public SuccessCode createReview (PostReviewRequest postReviewRequest, List<MultipartFile> images, Long userId){
        UserProfile user = findUserById(userId);
        PostReview review = postReviewRequest.to(user);
        List<ReviewImg> reviewImgs = postReviewRequest.to(images,s3Provider,userId,review);
        if(reviewImgs != null){
            review.setReviewImgs(reviewImgs);
        }
        postReviewRepository.save(review);
        return SuccessCode.CREATE_REVIEW_SUCCESS;
    }

    private UserProfile findUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserExceptionHandler(ErrorCode. USER_NOT_FOUND));

    }
}
