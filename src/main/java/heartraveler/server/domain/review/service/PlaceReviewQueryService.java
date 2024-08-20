package heartraveler.server.domain.review.service;

import heartraveler.server.domain.place.entity.Place;
import heartraveler.server.domain.place.exception.PlaceExceptionHandler;
import heartraveler.server.domain.place.repository.PlaceRepository;
import heartraveler.server.domain.reservation.dto.ReservationListResponse;
import heartraveler.server.domain.reservation.dto.ReservationPreviewResponse;
import heartraveler.server.domain.reservation.entity.Reservation;
import heartraveler.server.domain.reservation.repository.ReservationRepository;
import heartraveler.server.domain.review.dto.PlaceReviewSelectResponse;
import heartraveler.server.domain.review.dto.PlaceSearchListResponse;
import heartraveler.server.domain.user.exception.UserExceptionHandler;
import heartraveler.server.domain.user.repository.UserRepository;
import heartraveler.server.global.common.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceReviewQueryService {

    private final ReservationRepository reservationRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<ReservationPreviewResponse> getRecentReservations(Long userId){
        List<Reservation> reservations = reservationRepository.findTop3ByUserProfileOrderByDateDesc(userId);
        return reservations.stream()
                .map(ReservationPreviewResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public PlaceSearchListResponse getPlaceSearchList(String keyword, Long cursor, int pageSize){
        Pageable pageable = PageRequest.of(0, pageSize);
        List<Place> places = placeRepository.findByPlaceNameContainingWithPagination( keyword, cursor, pageable);
        Long nextCursor = places.isEmpty() ? null : places.get(places.size() - 1).getId();
        Boolean isLast = places.size() < pageSize;

        return PlaceSearchListResponse.from(places,nextCursor,isLast);
    }

    @Transactional
    public PlaceReviewSelectResponse getPlaceDetails(Long placeId, Long userId){
        Place place = findPlaceById (placeId);

        Optional<Reservation> reservation = findReservationByUserIdAndPlaceId(userId, placeId);

        if(reservation.isPresent()){
            return PlaceReviewSelectResponse.from(place, reservation.get());
        }else{
            return PlaceReviewSelectResponse.from(place);
        }
    }

    private Place findPlaceById(Long placeId){
        return placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceExceptionHandler(ErrorCode. PLACE_NOT_FOUND));
    }

    private Optional<Reservation> findReservationByUserIdAndPlaceId (Long userId,Long placeId){
        return reservationRepository.findByUserProfileIdAndPlaceId(userId, placeId);
    }

}
