package net.collabstack.app.letter.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LetterPinningRequest {

    @NotNull
    @Min(1)
    private Long letterId;

    @NotNull
    private Boolean isPinning;
}
