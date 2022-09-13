package com.epam.selectioncommittee.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFaculty {
    private int userId;
    private int facultyId;
}
