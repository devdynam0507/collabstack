package net.collabstack.app.letter.exception;

import lombok.Getter;

@Getter
public class LetterNotFoundException extends RuntimeException {

    private final Long letterId;
    private final String ownerEmail;

    public LetterNotFoundException(final Long letterId, final String ownerEmail, final String message) {
        super(message);
        this.letterId = letterId;
        this.ownerEmail = ownerEmail;
    }
}
