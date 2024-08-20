package heartraveler.server.domain.review.dto;

import heartraveler.server.domain.place.entity.Place;
import heartraveler.server.domain.reservation.dto.ReservationListResponse;
import heartraveler.server.domain.reservation.dto.ReservationPreviewResponse;
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
public class PlaceSearchListResponse {
    private List<PlaceInfo> placeList;
    private Long nextCursor;
    private Boolean isLast;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PlaceInfo{
        private Long placeId;
        private String placeName;
        private String imgUrl;

        public static PlaceInfo from (Place place){
            return PlaceInfo.builder()
                    .placeId(place.getId())
                    .placeName(place.getName())
                    .imgUrl(place.getImageUrl())
                    .build();
        }
    }

    public static PlaceSearchListResponse from(List<Place> placeList, Long nextCursor, Boolean isLast) {
        List<PlaceInfo> places = placeList.stream()
                .map(PlaceInfo::from)
                .collect(Collectors.toList());
        return PlaceSearchListResponse.builder()
                .placeList(places)
                .nextCursor(nextCursor)
                .isLast(isLast)
                .build();

    }

}
