package heartraveler.server.domain.review.entity;


import heartraveler.server.domain.user.entity.UserProfile;
import heartraveler.server.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "review_comment")
public class ReviewComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private PostReview postReview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "social_id")
    private UserProfile userProfile;
}
