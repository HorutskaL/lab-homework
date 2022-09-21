package com.epam.selectioncommittee.model;

import com.epam.selectioncommittee.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Entity(name = "Person")
//@Data
//@ToString(exclude = {"userInfo","subjects"})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, name = "password")
    private String password;

    private int isBlocked;
    private Role role = Role.ADMIN;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id", referencedColumnName = "id")
    @JsonManagedReference
    private UserInfo userInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @JsonManagedReference
    private Subject subject;

    @ManyToMany
    @JoinTable(
            name = "user_faculty",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    Set<Faculty> faculties;

    @OneToMany(mappedBy = "user")
    List<Statement> statements;
}
