package net.collabstack.app.letter;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.collabstack.app.letter.domain.Letter;
import net.collabstack.app.letter.dto.LetterResponse;
import net.collabstack.app.letter.dto.LetterSaveRequest;
import net.collabstack.common.CommonResponse;
import net.collabstack.common.ResultCode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/letters")
@RequiredArgsConstructor
@Validated
public class LetterController {

    private final LetterService letterService;

    @GetMapping("/{ownerEmail}")
    public CommonResponse<List<LetterResponse>> getLetters(
            @PathVariable("ownerEmail") @Email final String ownerEmail) {
        final List<Letter> letters = letterService.getLetters(ownerEmail);
        final List<LetterResponse> letterResponses = letters.stream()
                .map(LetterResponse::from)
                .collect(Collectors.toList());
        return CommonResponse.success(ResultCode.OK, "letters", letterResponses);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<LetterResponse> saveLetter(
            @RequestBody @Valid final LetterSaveRequest letterSaveRequest) {
        final Letter savedLetter = letterService.saveLetter(letterSaveRequest);
        final LetterResponse letterResponse = LetterResponse.from(savedLetter);
        return CommonResponse.success(ResultCode.CREATED, "saved", letterResponse);
    }
}
