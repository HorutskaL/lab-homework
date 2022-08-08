package com.epam.selectioncommittee.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"faculty"})
@Builder
public class Statement {

    private Long id;
    private boolean isFinalized;
    private Date creatingTime;
    private Faculty faculty;
}
