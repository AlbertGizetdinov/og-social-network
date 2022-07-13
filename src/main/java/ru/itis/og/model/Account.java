package ru.itis.og.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.itis.og.model.enumeration.Gender;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
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

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @OneToMany(mappedBy = "account")
    private List<Product> products;

    @Column(name = "confirm_code")
    private String confirmCode;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "following")
    private Set<Subscription> followers;

    @OneToMany(mappedBy = "follower")
    private Set<Subscription> followings;

    @OneToOne(mappedBy = "account")
    private Description description;

    @OneToMany(mappedBy = "account")
    private Set<Link> links;

    @OneToMany(mappedBy = "account")
    private Set<FileInformation> files;

    public enum State {
        CONFIRMED, NOT_CONFIRMED, DELETED, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    public boolean isNonBanned() {
        return !this.state.equals(State.BANNED);
    }

    public boolean isConfirmed() {
        return this.state.equals(State.CONFIRMED);
    }


}
