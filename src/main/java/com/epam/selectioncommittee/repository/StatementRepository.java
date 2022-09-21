package com.epam.selectioncommittee.repository;

import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.model.Statement;
import com.epam.selectioncommittee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {

    Statement findByUser(User user);

    boolean existsStatementByUser(User user);

    List<Statement> findByFaculty(@Param("faculty") Faculty faculty);

    @Query("select s from Statement s where s.amountBudgetPl = 1 and s.faculty =?1")
    List<Statement> findBudgetStPlByFaculty(Faculty faculty);

    @Query("select s from Statement s where s.amountNonBudgetPl = 1 and s.faculty =?1")
    List<Statement> findNonBudStPlByFaculty(Faculty faculty);
}
