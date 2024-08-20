package heartraveler.server.domain.review.exception;

import heartraveler.server.global.common.ErrorResponseCode;
import heartraveler.server.global.common.exception.CustomException;

public class ReviewExceptionHandler extends CustomException {
    public ReviewExceptionHandler (ErrorResponseCode code){
        super(code);
    }
}
