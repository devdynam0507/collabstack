package net.collabstack.app.member.exception;

import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException {

    private final String id;

    public MemberNotFoundException(final String message, final String id) {
        super(message);
        this.id = id;
    }
}
