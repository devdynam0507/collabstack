package net.collabstack.app.letter.exception;

import lombok.Data;

@Data
public class PinAlreadyFullException extends RuntimeException {

    private String ownerEmail;

    public PinAlreadyFullException(final String ownerEmail, final String message) {
        super(message);
        this.ownerEmail = ownerEmail;
    }
}
