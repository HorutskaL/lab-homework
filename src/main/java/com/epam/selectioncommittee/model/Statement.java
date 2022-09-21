package com.epam.selectioncommittee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Statement")
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private boolean isFinalized;
    private Date creatingTime;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @JsonIgnore
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    private int amountBudgetPl;
    private int amountNonBudgetPl;
}
