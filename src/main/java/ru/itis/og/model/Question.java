package ru.itis.og.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "question")
public class Question extends AbstractEntity {

    @Column(nullable = false)
    private String text;

    private String answer;

    private UUID questioner;

    private UUID answerer;

    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    @Column(nullable = false, name = "is_anonymous")
    private Boolean isAnonymous;

    @Column(nullable = false, name = "is_answered")
    private Boolean isAnswered;

}
