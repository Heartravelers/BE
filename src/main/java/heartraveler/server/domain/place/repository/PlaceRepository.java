package heartraveler.server.domain.place.repository;

import heartraveler.server.domain.place.entity.Place;
import heartraveler.server.domain.reservation.entity.Reservation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Long> {
    @Query("SELECT p FROM Place p WHERE p.name LIKE %:keyword% AND (:cursor IS NULL OR p.id > :cursor) ORDER BY p.id ASC")
    List<Place> findByPlaceNameContainingWithPagination(
            @Param("keyword") String keyword,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
