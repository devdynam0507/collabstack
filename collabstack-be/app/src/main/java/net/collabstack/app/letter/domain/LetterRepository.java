package net.collabstack.app.letter.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    int countByOwnerEmailAndIsPinned(final String ownerEmail, final boolean isPinned);

    List<Letter> findByOwner_emailAndIsPinned(final String ownerEmail, final boolean isPinned);

    List<Letter> findByOwner_email(final String ownerEmail);
}
