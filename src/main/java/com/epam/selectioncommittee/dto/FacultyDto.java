package com.epam.selectioncommittee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacultyDto {
    private Long id;
    private String name;
    private int amountBudgetPlace;
    private int amountTotalPlace;
    private int isEieMath;
    private int isEieUkLanguage;
    private int isEieHistory;
}
