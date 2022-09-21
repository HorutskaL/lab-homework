package com.epam.selectioncommittee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int algebra;
    private int science;
    private int geometry;
    private int geography;
    private int ukLiterature;
    private int ukHistory;
    private int foreignLanguage;
    private int ukLanguage;
    private int informatics;
    private int phTraining;
    private int eieMath;
    private int eieUkLanguage;
    private int eieHistory;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;
}
