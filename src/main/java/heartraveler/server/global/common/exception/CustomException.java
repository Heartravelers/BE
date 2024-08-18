package heartraveler.server.global.common.exception;

import heartraveler.server.global.common.BaseResponseCode;
import heartraveler.server.global.common.ErrorResponseCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorResponseCode errorCode;

    public CustomException(ErrorResponseCode errorCode) {
        this.errorCode = errorCode;
    }
}

