package heartraveler.server.domain.user.repository;

import heartraveler.server.domain.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserProfile, Long> {
}
