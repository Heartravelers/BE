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
public class ReservationPreviewResponse {
    private Long reservationId;
    private Long placeId;
    private String placeName;
    private String imgUrl;
    private String reservationOption;

    public static ReservationPreviewResponse from (Reservation reservation){
        return ReservationPreviewResponse.builder()
                .reservationId(reservation.getId())
                .placeId(reservation.getPlace().getId())
                .placeName(reservation.getPlace().getName())
                .imgUrl(reservation.getPlace().getImageUrl())
                .reservationOption(reservation.getReservationOption())
                .build();
    }
}
