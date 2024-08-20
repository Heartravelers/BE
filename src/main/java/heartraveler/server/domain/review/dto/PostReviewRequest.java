package heartraveler.server.domain.review.dto;

import heartraveler.server.domain.review.entity.PostReview;
import heartraveler.server.domain.review.entity.ReviewImg;
import heartraveler.server.domain.user.entity.UserProfile;
import heartraveler.server.global.util.s3.S3Provider;
import heartraveler.server.global.util.s3.dto.S3UploadRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostReviewRequest {
    private String content;

    //TO DO : 플레이스 정보 추가
    public PostReview to(UserProfile user){
        return PostReview.builder()
                .content(this.content)
                .userProfile(user)
                .build();
    }

    public List<ReviewImg> to(List<MultipartFile> images, S3Provider s3Provider, Long userId, PostReview review ){
        if (images == null || images.isEmpty()) {
            return null;
        }
        return images.stream()
                .map(image -> {
                    String imageUrl = s3Provider.uploadFile(image,
                            S3UploadRequest.builder()
                                    .userId(userId)
                                    .dirName("PostReview")
                                    .build());
                    return ReviewImg.builder()
                            .imgUrl(imageUrl)
                            .postReview(review)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
