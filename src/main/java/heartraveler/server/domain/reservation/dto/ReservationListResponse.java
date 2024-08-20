package heartraveler.server.domain.reservation.dto;

import heartraveler.server.domain.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationListResponse {
    private List<ReservationPreviewResponse> reservationList;
    private Long nextCursor;
    private Boolean isLast;

    public static ReservationListResponse from (List<Reservation> reservationList , Long nextCursor, Boolean isLast){
        List<ReservationPreviewResponse> reservations = reservationList.stream()
                .map(ReservationPreviewResponse::from)
                .collect(Collectors.toList());
        return ReservationListResponse.builder()
                .reservationList(reservations)
                .nextCursor(nextCursor)
                .isLast(isLast)
                .build();

    }

}
