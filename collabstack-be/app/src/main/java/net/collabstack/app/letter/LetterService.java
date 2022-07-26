package net.collabstack.app.letter;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.collabstack.app.letter.domain.Letter;
import net.collabstack.app.letter.domain.LetterRepository;
import net.collabstack.app.letter.dto.LetterSaveRequest;
import net.collabstack.app.member.MemberService;
import net.collabstack.app.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LetterService {

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
}
