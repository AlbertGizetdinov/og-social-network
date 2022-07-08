package ru.itis.og.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.graalvm.compiler.lir.CompositeValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "description")
public class Description extends AbstractEntity {

    private String status;

    private String hometown;

    private String country;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "personal_information")
    private String personInfo;

    @Column(name = "personal_website")
    private String personalWebsite;

    private String job;

    @OneToOne(mappedBy = "description")
    private Account account;

}
