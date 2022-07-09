package ru.itis.og.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.itis.og.model.enumeration.State;

import javax.persistence.*;
import java.net.URI;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "link")
public class Link extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Column(nullable = false)
    private String title;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(nullable = false)
    private URI link;

}
