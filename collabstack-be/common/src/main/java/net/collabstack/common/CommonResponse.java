package net.collabstack.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class CommonResponse<T> {

    private final ResultCode code;
    private final String message;
    private final T data;

    public static <U> CommonResponse<U> success() {
        return success(ResultCode.OK, "Success");
    }

    public static <U> CommonResponse<U> success(final ResultCode resultCode, final String message) {
        return success(resultCode, message, null);
    }

    public static <U> CommonResponse<U> success(
            final ResultCode resultCode, final String message, final U data) {
        return new CommonResponse<>(resultCode, message, data);
    }

    public static <U> CommonResponse<U> failure() {
        return failure(ResultCode.BAD_REQUEST, "Failed");
    }

    public static <U> CommonResponse<U> failure(final ResultCode resultCode, final String message) {
        return failure(resultCode, message, null);
    }

    public static <U> CommonResponse<U> failure(
            final ResultCode resultCode, final String message, final U data) {
        return failure(resultCode, message, data);
    }

    public ResponseEntity<CommonResponse<T>> wrapOk() {
        return wrap(HttpStatus.OK);
    }

    public ResponseEntity<CommonResponse<T>> wrap(final HttpStatus status) {
        return ResponseEntity.status(status).body(this);
    }
}
