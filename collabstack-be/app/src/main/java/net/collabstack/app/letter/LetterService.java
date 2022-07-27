package net.collabstack.app.letter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.collabstack.app.letter.domain.Letter;
import net.collabstack.app.letter.domain.LetterRepository;
import net.collabstack.app.letter.dto.LetterPinningRequest;
import net.collabstack.app.letter.dto.LetterSaveRequest;
import net.collabstack.app.letter.exception.LetterNotFoundException;
import net.collabstack.app.letter.exception.PinAlreadyFullException;
import net.collabstack.app.member.MemberService;
import net.collabstack.app.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final static int MAX_PINNING_COUNT = 4;

    private final LetterRepository letterRepository;
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public List<Letter> getLetters(final String ownerId) {
        return letterRepository.findByOwner_email(ownerId);
    }

    @Transactional
    public Letter saveLetter(final LetterSaveRequest letterSaveRequest) {
        final Member recommender = memberService.getMember(letterSaveRequest.getRecommenderEmail());
        final Member owner = memberService.getMember(letterSaveRequest.getOwnerEmail());
        final Letter letter = Letter.toLetter(make -> {
            make.setLetterTitle(letterSaveRequest.getLetterTitle());
            make.setContent(letterSaveRequest.getLetterContent());
            make.setOwner(owner);
            make.setRecommender(recommender);
            make.setPinned(false);
            make.setStar(0);
            make.setCreateDate(LocalDateTime.now());
            return make;
        });
        return letterRepository.save(letter);
    }

    @Transactional(readOnly = true)
    final List<Letter> getPinnedLetters(final String ownerEmail) {
        return letterRepository.findByOwner_emailAndIsPinned(ownerEmail, true);
    }

    @Transactional
    public Letter setPinned(final String ownerEmail, final LetterPinningRequest pinningRequest) {
        final List<Letter> letters = letterRepository.findByOwner_email(ownerEmail);
        final long pinningCount = letters.stream()
                                         .filter(Letter::isPinned)
                                         .count();
        if (pinningCount >= MAX_PINNING_COUNT) {
            throw new PinAlreadyFullException(ownerEmail, "이미 지정된 최대 pinned 개수에 도달하였습니다 (핀 지정갯수 4)");
        }
        final Optional<Letter> letterOptional = letters.stream()
               .filter(
                letter -> letter.getOwner().getEmail().equals(ownerEmail)
                          && letter.getId().compareTo(pinningRequest.getLetterId()) == 0)
                       .findFirst();
        letterOptional.ifPresent(letter -> letter.setPinned(pinningRequest.getIsPinning()));
        return letterOptional.orElseThrow(() -> new LetterNotFoundException(pinningRequest.getLetterId(),
                                                                            ownerEmail,
                                                                            "추천서를 찾을 수 없습니다."));
    }
}
