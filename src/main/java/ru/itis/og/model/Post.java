package ru.itis.og.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "post")
public class Post extends AbstractEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Column(name = "update_date")
    private Instant updateDate;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "account_uuid")
    private Account account;

    public enum State {
        DELETED, PUBLISHED
    }
}
