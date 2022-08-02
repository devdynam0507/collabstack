package net.collabstack.app.letter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import net.collabstack.common.CommonResponse;
import net.collabstack.common.ResultCode;

@RestControllerAdvice
public class LetterExceptionHandler {

    @ExceptionHandler(PinAlreadyFullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<String> pinAlreadyFullExceptionHandler(
            final PinAlreadyFullException exception) {
        return CommonResponse.failure(
                ResultCode.BAD_REQUEST, exception.getMessage(), exception.getOwnerEmail());
    }

    @ExceptionHandler(LetterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse<String> letterNotFoundException(
            final LetterNotFoundException exception) {
        return CommonResponse.failure(
                ResultCode.NOT_FOUND, exception.getLetterId() + " " + exception.getMessage(),
                exception.getOwnerEmail());
    }
}
