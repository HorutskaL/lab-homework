package com.epam.selectioncommittee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private int amountBudgetPlaces;
    private int amountTotalPlaces;
    private int isEieMath;
    private int isEieUkLanguage;
    private int isEieHistory;

    @ManyToMany(mappedBy = "faculties")
    @JsonIgnore
    Set<User> users;

    @OneToMany(mappedBy = "faculty")
    @JsonIgnore
    List<Statement> statements;
}
