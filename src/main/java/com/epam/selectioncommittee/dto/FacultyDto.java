package com.epam.selectioncommittee.dto;

import com.epam.selectioncommittee.dto.group.OnCreate;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class FacultyDto {
    private Long id;
    @NotBlank(message = "'name' shouldn't be empty", groups = OnCreate.class)
    private String name;
    @PositiveOrZero
    private int amountBudgetPlace;
    @Positive
    private int amountTotalPlace;
    @Min(1)
    @Max(12)
    private int isEieMath;
    @Min(1)
    @Max(12)
    private int isEieUkLanguage;
    @Min(1)
    @Max(12)
    private int isEieHistory;
}
