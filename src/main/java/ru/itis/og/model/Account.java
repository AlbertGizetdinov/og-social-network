package ru.itis.og.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "account")
public class Account extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String firstname;

    private String nickname;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Instant birthday;

    @OneToMany(mappedBy = "following")
    private Set<Subscription> followers;

    @OneToMany(mappedBy = "follower")
    private Set<Subscription> followings;

}
