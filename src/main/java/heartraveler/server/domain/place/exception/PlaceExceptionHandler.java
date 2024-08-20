package heartraveler.server.domain.place.exception;

import heartraveler.server.global.common.ErrorResponseCode;
import heartraveler.server.global.common.exception.CustomException;

public class PlaceExceptionHandler extends CustomException {
    public PlaceExceptionHandler (ErrorResponseCode code){
        super(code);
    }
}