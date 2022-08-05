package com.epam.selectioncommittee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectDto {
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
    private UserDto user;
}
