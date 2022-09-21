package com.epam.selectioncommittee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String firstName;
    private String lastName;
    private String patronymic;
    private String city;
    private String region;
    private String school;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;
}
