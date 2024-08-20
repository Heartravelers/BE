package heartraveler.server.domain.review.service;

import heartraveler.server.domain.reservation.dto.RecentReservationResponse;
import heartraveler.server.domain.reservation.entity.Reservation;
import heartraveler.server.domain.reservation.repository.ReservationRepository;
import heartraveler.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceReviewQueryService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<RecentReservationResponse> getRecentReservations(Long userId){
        List<Reservation> reservations = reservationRepository.findTop3ByUserProfileOrderByDateDesc(userId);
        return reservations.stream()
                .map(RecentReservationResponse::from)
                .collect(Collectors.toList());
    }
}
