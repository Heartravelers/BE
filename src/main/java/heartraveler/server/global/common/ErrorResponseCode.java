package heartraveler.server.global.common;

public interface ErrorResponseCode extends BaseResponseCode {

    ApiResponse<Void> getErrorResponse();
}