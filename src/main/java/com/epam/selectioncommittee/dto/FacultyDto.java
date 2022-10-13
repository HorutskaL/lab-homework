package com.epam.selectioncommittee.dto;

import com.epam.selectioncommittee.dto.group.OnCreate;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class FacultyDto {
    private Long id;
    @NotBlank(message = "validation.faculty.name", groups = OnCreate.class)
    private String name;
    @PositiveOrZero
    private int amountBudgetPlaces;
    @Positive
    private int amountTotalPlaces;
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
