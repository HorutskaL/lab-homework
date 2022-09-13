package com.epam.selectioncommittee.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
public class StatementDto {
    private Long id;
    private boolean isFinalized;
    private Date creatingTime;
    private FacultyDto faculty;
}
