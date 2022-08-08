package com.epam.selectioncommittee.repository;

import com.epam.selectioncommittee.model.Statement;

import java.util.List;

public interface StatementRepository {
    Statement createStatement(Statement statement);

    List<Statement> statements(Integer statementId);

    Statement getStatementByFacultyName(String facultyName);

    Statement finalizeStatement(Integer statementId, Statement statement);
}
