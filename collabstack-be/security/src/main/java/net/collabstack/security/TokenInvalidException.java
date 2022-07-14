package net.collabstack.security;

/**
 * JWT가 null이거나 유효하지 않을 때 발생하는 런타임 예외
 * */
public class TokenInvalidException extends RuntimeException {

    public TokenInvalidException(String message) {
        super(message);
    }

}
