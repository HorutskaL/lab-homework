package com.epam.selectioncommittee.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Applicant {
    private User applicantId;
    private double averageMark;
}
