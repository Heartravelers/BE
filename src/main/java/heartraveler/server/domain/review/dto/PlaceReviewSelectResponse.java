package heartraveler.server.domain.review.dto;

import heartraveler.server.domain.place.entity.Place;
import heartraveler.server.domain.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaceReviewSelectResponse {
    private Long placeId;
    private String placeName;
    private String placeImgUrl;
    private Long reservationId;
    private String reservationOption;

    public static PlaceReviewSelectResponse from(Place place, Reservation reservation) {
        return PlaceReviewSelectResponse.builder()
                .placeId(place.getId())
                .placeName(place.getName())
                .placeImgUrl(place.getImageUrl())
                .reservationId(reservation.getId())
                .reservationOption(reservation.getReservationOption())
                .build();
    }

    public static PlaceReviewSelectResponse from(Place place) {
        return PlaceReviewSelectResponse.builder()
                .placeId(place.getId())
                .placeName(place.getName())
                .placeImgUrl(place.getImageUrl())
                .build();
    }

}
