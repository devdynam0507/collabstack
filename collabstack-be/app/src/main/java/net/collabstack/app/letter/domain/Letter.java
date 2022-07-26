package net.collabstack.app.letter.domain;

import java.time.LocalDateTime;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import net.collabstack.app.member.domain.Member;

import lombok.Data;

@Entity
@Data
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String letterTitle;

    @Column(length = 300, nullable = false)
    private String content;

    @Column
    private Integer star;

    private boolean isPinned;

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "recommenderEmail")
    private Member recommender;

    @ManyToOne
    @JoinColumn(name = "ownerEmail")
    private Member owner;

    public static Letter toLetter(final Function<Letter, Letter> function) {
        final Letter letter = new Letter();
        return function.apply(letter);
    }
}
