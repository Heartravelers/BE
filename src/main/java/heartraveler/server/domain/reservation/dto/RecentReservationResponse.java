package heartraveler.server.domain.reservation.dto;

import heartraveler.server.domain.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecentReservationResponse {
    private Long reservationId;
    private Long placeId;
    private String placeName;
    private String imgUrl;

    public static RecentReservationResponse from (Reservation reservation){
        return RecentReservationResponse.builder()
                .reservationId(reservation.getId())
                .placeId(reservation.getPlace().getId())
                .placeName(reservation.getPlace().getName())
                .imgUrl(reservation.getPlace().getImageUrl())
                .build();
    }
}
