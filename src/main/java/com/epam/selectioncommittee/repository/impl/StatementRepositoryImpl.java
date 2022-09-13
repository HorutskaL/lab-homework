package com.epam.selectioncommittee.repository.impl;

import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.model.Statement;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.repository.StatementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class StatementRepositoryImpl implements StatementRepository {

    @Override
    public List<Statement> statements(Long statementId) {
        return new ArrayList<>();
    }

    @Override
    public Statement getStatementByFacultyId(Long facultyId) {
        return null;
    }

    @Override
    public Statement finalizeStatement(Long statementId, Statement statement) {
        return null;
    }

    @Override
    public Statement findByUser(User user) {
        return null;
    }

    @Override
    public void save(Statement statement) {

    }

    @Override
    public void removeApplicantFromStatement(User userId) {

    }

    @Override
    public boolean existByUser(User user) {
        return false;
    }

    @Override
    public List<Statement> findByFaculty(Faculty faculty) {
        return null;
    }

    @Override
    public List<Statement> findBudgetStPlByFaculty(Faculty faculty) {
        return null;
    }

    @Override
    public List<Statement> findNonBudStPlByFaculty(Faculty faculty) {
        return null;
    }

    @Override
    public void delete(Statement statement) {

    }
}
