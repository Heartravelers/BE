package heartraveler.server.domain.user.repository;

import heartraveler.server.domain.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByName(String name);

}
