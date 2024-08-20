package heartraveler.server.domain.review.repository;

import heartraveler.server.domain.review.entity.PostReview;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PostReviewRepository extends JpaRepository <PostReview, Long> {
}
