package com.epam.selectioncommittee.repository;

import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.model.Statement;
import com.epam.selectioncommittee.model.User;

import java.util.List;

public interface StatementRepository {
    List<Statement> statements(Long statementId);

    Statement getStatementByFacultyId(Long facultyId);

    Statement finalizeStatement(Long statementId, Statement statement);


    Statement findByUser(User user);

    void save(Statement statement);

    void removeApplicantFromStatement(User userId);

    boolean existByUser(User user);

    void delete(Statement statement);

    List<Statement> findByFaculty(Faculty faculty);

    List<Statement> findBudgetStPlByFaculty(Faculty faculty);

    List<Statement> findNonBudStPlByFaculty(Faculty faculty);
}
