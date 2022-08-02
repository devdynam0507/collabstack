package net.collabstack.app.letter.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LetterSaveRequest {

    @Size(min = 5, max = 30)
    private String letterTitle;

    @Size(min = 30, max = 300)
    private String letterContent;

    @Email
    @NotNull
    private String recommenderEmail;

    @Email
    @NotNull
    private String ownerEmail;
}
