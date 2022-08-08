package com.epam.selectioncommittee.repository.impl;

import com.epam.selectioncommittee.model.Statement;
import com.epam.selectioncommittee.repository.StatementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class StatementRepositoryImpl implements StatementRepository {
    @Override
    public Statement createStatement(Statement statement) {
        return null;
    }

    @Override
    public List<Statement> statements(Integer statementId) {
        return null;
    }

    @Override
    public Statement getStatementByFacultyName(String facultyName) {
        return null;
    }

    @Override
    public Statement finalizeStatement(Integer statementId, Statement statement) {
        return null;
    }
}
