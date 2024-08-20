package heartraveler.server.domain.user.exception;

import heartraveler.server.global.common.ErrorResponseCode;
import heartraveler.server.global.common.exception.CustomException;

public class UserExceptionHandler extends CustomException {

    public UserExceptionHandler (ErrorResponseCode code ){
        super(code);
    }
}