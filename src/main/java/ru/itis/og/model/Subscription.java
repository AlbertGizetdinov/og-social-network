package ru.itis.og.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "subscription")
public class Subscription extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id")
    private Account follower;

    @ManyToOne
    @JoinColumn(name = "following_id", referencedColumnName = "id")
    private Account following;

}
