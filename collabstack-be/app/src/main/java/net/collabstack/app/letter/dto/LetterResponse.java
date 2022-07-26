package net.collabstack.app.letter.dto;

import java.util.Date;

import net.collabstack.app.letter.domain.Letter;
import net.collabstack.app.letter.util.DateUtil;

import lombok.Data;

@Data
public class LetterResponse {

    private Long id;
    private String letterTitle;
    private String letterContent;
    private Integer star;
    private boolean isPinned;
    /**
     * 생성일을 좀더 정확하게 표현합니다.
     * e.g) 1 hours ago, 1 years ago
     * */
    private String creationDate;
    private String recommenderName;

    public static LetterResponse from(final Letter letter) {
        final LetterResponse letterResponse = new LetterResponse();
        letterResponse.setId(letter.getId());
        letterResponse.setLetterTitle(letter.getLetterTitle());
        letterResponse.setLetterContent(letter.getContent());
        letterResponse.setStar(letter.getStar());
        letterResponse.setPinned(letter.isPinned());
        letterResponse.setCreationDate(DateUtil.toStandingTypeDate(new Date(), letter.getCreateDate()));
        letterResponse.setRecommenderName(letter.getRecommender().getName());
        return letterResponse;
    }
}
