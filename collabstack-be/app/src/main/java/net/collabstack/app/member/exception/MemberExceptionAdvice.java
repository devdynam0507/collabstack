package net.collabstack.app.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import net.collabstack.common.CommonResponse;
import net.collabstack.common.ResultCode;

@RestControllerAdvice
public class MemberExceptionAdvice {

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse<String> memberNotFoundExceptionHandler(
            final MemberNotFoundException memberNotFoundException) {
        return CommonResponse.failure(
                ResultCode.NOT_FOUND,
                memberNotFoundException.getId() + " member를 찾을 수 없습니다.",
                memberNotFoundException.getId());
    }
}
