package com.epam.selectioncommittee.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"user"})
@Builder
public class Subject {
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
    private User userId;
}
