package heartraveler.server.domain.reservation.repository;

import heartraveler.server.domain.reservation.entity.Reservation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.userProfile.id = :userId ORDER BY r.date DESC")
    List<Reservation> findTop3ByUserProfileOrderByDateDesc(@Param("userId") Long userId);

    @Query("SELECT r FROM Reservation r WHERE r.userProfile.id = :userId AND r.place.name LIKE %:keyword% AND (:cursor IS NULL OR r.id > :cursor) ORDER BY r.id ASC")
    List<Reservation> findByUserIdAndPlaceNameContainingWithPagination(
            @Param("userId") Long userId,
            @Param("keyword") String keyword,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}