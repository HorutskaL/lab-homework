package com.epam.selectioncommittee.dto;

import com.epam.selectioncommittee.dto.group.OnCreate;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class SubjectDto {

    private Long id;
    @Min(1)
    @Max(12)
    @NotBlank(message = "validation.mark", groups = OnCreate.class)
    private int algebra;
    @Min(1)
    @Max(12)
    @NotBlank(message = "validation.mark", groups = OnCreate.class)
    private int science;
    @Min(1)
    @Max(12)
    @NotBlank(message = "validation.mark", groups = OnCreate.class)
    private int geometry;
    @Min(1)
    @Max(12)
    @NotBlank(message = "validation.mark", groups = OnCreate.class)
    private int geography;
    @Min(1)
    @Max(12)
    @NotBlank(message = "validation.mark", groups = OnCreate.class)
    private int ukLiterature;
    @Min(1)
    @Max(12)
    @NotBlank(message = "validation.mark", groups = OnCreate.class)
    private int ukHistory;
    @Min(1)
    @Max(12)
    @NotBlank(message = "validation.mark", groups = OnCreate.class)
    private int foreignLanguage;
    @Min(1)
    @Max(12)
    @NotBlank(message = "validation.mark", groups = OnCreate.class)
    private int ukLanguage;
    @Min(1)
    @Max(12)
    @NotBlank(message = "validation.mark", groups = OnCreate.class)
    private int informatics;
    @Min(1)
    @Max(12)
    @NotBlank(message = "validation.mark", groups = OnCreate.class)
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
