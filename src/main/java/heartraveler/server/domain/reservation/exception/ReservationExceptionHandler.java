package heartraveler.server.domain.reservation.exception;

import heartraveler.server.global.common.ErrorResponseCode;
import heartraveler.server.global.common.exception.CustomException;

public class ReservationExceptionHandler extends CustomException {
    public ReservationExceptionHandler (ErrorResponseCode code){
        super(code);
    }
}