package com.epam.selectioncommittee.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"users"})
@Builder
public class Faculty {

    private Long id;
    private String name;
    private int amountBudgetPlaces;
    private int amountTotalPlaces;
    private int isEieMath;
    private int isEieUkLanguage;
    private int isEieHistory;
    Set<User> users;
    List<Statement> statements = new ArrayList<Statement>();
}
