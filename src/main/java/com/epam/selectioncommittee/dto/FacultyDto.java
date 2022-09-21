package com.epam.selectioncommittee.dto;

import com.epam.selectioncommittee.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class FacultyDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank(message = "validation.faculty.name", groups = OnCreate.class)
    private String name;
    @PositiveOrZero
    private int amountBudgetPlace;
    @Positive
    private int amountTotalPlace;
    @Min(0)
    @Max(1)
    private int isEieMath;
    @Min(0)
    @Max(1)
    private int isEieUkLanguage;
    @Min(0)
    @Max(1)
    private int isEieHistory;
}
