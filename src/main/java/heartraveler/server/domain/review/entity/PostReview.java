package heartraveler.server.domain.review.entity;

import heartraveler.server.domain.user.entity.UserProfile;
import heartraveler.server.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "post_review")
public class PostReview extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "postReview", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImg> reviewImgs = new ArrayList<>();

    @OneToMany(mappedBy = "postReview", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewComment> reviewComments = new ArrayList<>();

    @OneToMany(mappedBy = "postReview", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewLike> reviewLikes = new ArrayList<>();

    @OneToMany(mappedBy = "postReview", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewScrap> reviewScraps = new ArrayList<>();
}
