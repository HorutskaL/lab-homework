package com.epam.selectioncommittee.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
public class SubjectDto {
    private Long id;
    @Min(1)
    @Max(12)
    private int algebra;
    @Min(1)
    @Max(12)
    private int science;
    @Min(1)
    @Max(12)
    private int geometry;
    @Min(1)
    @Max(12)
    private int geography;
    @Min(1)
    @Max(12)
    private int ukLiterature;
    @Min(1)
    @Max(12)
    private int ukHistory;
    @Min(1)
    @Max(12)
    private int foreignLanguage;
    @Min(1)
    @Max(12)
    private int ukLanguage;
    @Min(1)
    @Max(12)
    private int informatics;
    @Min(1)
    @Max(12)
    private int phTraining;
    @Min(1)
    @Max(12)
    private int eieMath;
    @Min(1)
    @Max(12)
    private int eieUkLanguage;
    @Min(1)
    @Max(12)
    private int eieHistory;
    private UserDto user;
}
