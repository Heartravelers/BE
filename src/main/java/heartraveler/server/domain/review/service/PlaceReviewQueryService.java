package heartraveler.server.domain.review.service;

import heartraveler.server.domain.reservation.dto.ReservationListResponse;
import heartraveler.server.domain.reservation.dto.ReservationPreviewResponse;
import heartraveler.server.domain.reservation.entity.Reservation;
import heartraveler.server.domain.reservation.repository.ReservationRepository;
import heartraveler.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<ReservationPreviewResponse> getRecentReservations(Long userId){
        List<Reservation> reservations = reservationRepository.findTop3ByUserProfileOrderByDateDesc(userId);
        return reservations.stream()
                .map(ReservationPreviewResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReservationListResponse getReservationList(Long userId, String keyword, Long cursor, int pageSize){
        Pageable pageable = PageRequest.of(0, pageSize);
        List<Reservation> reservations = reservationRepository.findByUserIdAndPlaceNameContainingWithPagination(userId, keyword, cursor, pageable);
        Long nextCursor = reservations.isEmpty() ? null : reservations.get(reservations.size() - 1).getId();
        Boolean isLast = reservations.size() < pageSize;

        return ReservationListResponse.from(reservations,nextCursor,isLast);
    }
}
