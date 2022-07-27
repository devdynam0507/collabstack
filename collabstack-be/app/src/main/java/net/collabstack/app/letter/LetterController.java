package net.collabstack.app.letter;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.collabstack.app.letter.domain.Letter;
import net.collabstack.app.letter.dto.LetterPinningRequest;
import net.collabstack.app.letter.dto.LetterResponse;
import net.collabstack.app.letter.dto.LetterSaveRequest;
import net.collabstack.common.CommonResponse;
import net.collabstack.common.ResultCode;
import net.collabstack.security.annotation.AuthenticatedUser;
import net.collabstack.security.member.SecurityMember;

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

    @PutMapping("/pins")
    public CommonResponse<LetterResponse> pinning(
            @AuthenticatedUser @NotNull final SecurityMember<String> authenticatedUser,
            @RequestBody @Valid final LetterPinningRequest letterPinningRequest) {
        final Letter pinnedLetter =
                letterService.setPinned(authenticatedUser.getUsername(), letterPinningRequest);
        final LetterResponse letterResponse = LetterResponse.from(pinnedLetter);
        return CommonResponse.success(ResultCode.OK, "pinning", letterResponse);
    }

    @GetMapping("/{ownerEmail}/pins")
    public CommonResponse<List<LetterResponse>> getPinnedLetters(
            @PathVariable("ownerEmail") @Email final String ownerEmail) {
        final List<Letter> pinnedLetters = letterService.getPinnedLetters(ownerEmail);
        final List<LetterResponse> letterResponses = pinnedLetters.stream()
                .map(LetterResponse::from)
                .collect(Collectors.toList());
        return CommonResponse.success(ResultCode.OK, "pinned letters", letterResponses);
    }
}
