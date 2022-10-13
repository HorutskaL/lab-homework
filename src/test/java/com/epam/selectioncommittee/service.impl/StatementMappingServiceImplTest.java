package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.model.*;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.repository.StatementRepository;
import com.epam.selectioncommittee.test.util.TestDataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.epam.selectioncommittee.service.impl.StatementMappingServiceImpl")
public class StatementMappingServiceImplTest {
    public StatementMappingServiceImplTest() {
    }

    @InjectMocks
    private StatementMappingServiceImpl statementMappingService;
    @Mock
    private StatementRepository statementRepository;
    @Mock
    private FacultyRepository facultyRepository;

    @Test
    public void finaliseStatementTest() throws Exception {
        List<Statement> statementList = new ArrayList<>();
        User user = User.builder().userInfo(UserInfo.builder().build()).subject(Subject.builder().build()).build();
        Statement statement = Statement.builder().user(user).build();
        statementList.add(statement);
        StatementMappingServiceImpl statementMappingService1 = PowerMockito.spy(statementMappingService);
        Mockito.when(facultyRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Faculty.builder().statements(statementList).build()));
        PowerMockito.doReturn(1d).when(statementMappingService1,"getAverageMark",any(),any());
        PowerMockito.doNothing().when(statementMappingService1,"finalise",any(),any());
        statementMappingService1.finaliseStatement(1L);
        verifyPrivate(statementMappingService1).invoke("finalise", any(),any());
    }

    @Test
    public final void getAverageMarkTest() throws Exception {
        Subject subject = TestDataUtil.createSubject();
        Faculty faculty = Faculty.builder().id(1L).isEieHistory(1).isEieMath(1).isEieUkLanguage(1).build();
        double expected = 7.846153846153846;
        double result = Whitebox.invokeMethod(new StatementMappingServiceImpl(statementRepository,facultyRepository),
                "getAverageMark", subject, faculty);
        Assertions.assertEquals(expected,result,0.0);

    }

    @Test
    public final void finaliseTest() throws Exception {
        Faculty faculty = Faculty.builder().amountBudgetPlaces(1).amountTotalPlaces(2).build();
        List<Applicant> applicantList = new ArrayList<>();
        Applicant applicant1 = new Applicant();
        applicant1.setApplicantId(User.builder().id(1L).build());
        applicant1.setAverageMark(8);
        Applicant applicant2 = new Applicant();
        applicant2.setApplicantId(User.builder().id(1L).build());
        applicant2.setAverageMark(9);
        Applicant applicant3 = new Applicant();
        applicant3.setApplicantId(User.builder().id(1L).build());
        applicant3.setAverageMark(7.5);
        applicantList.add(applicant1);
        applicantList.add(applicant2);
        applicantList.add(applicant3);
        StatementMappingServiceImpl statementMappingService1 = PowerMockito.spy(statementMappingService);
        PowerMockito.doNothing().when(statementMappingService1,"setBudgetPlaceFlag",any());
        PowerMockito.doNothing().when(statementMappingService1,"setNonBudgetPlaceFlag",any());
        Whitebox.invokeMethod(statementMappingService1,"finalise",applicantList,faculty);
        verifyPrivate(statementMappingService1).invoke("setBudgetPlaceFlag",any());
        verifyPrivate(statementMappingService1).invoke("setNonBudgetPlaceFlag",any());

    }

    @Test
    public final void setBudgetPlaceFlagTest() throws Exception {
        Statement statement = Statement.builder().build();
        List<Applicant> applicantList = new ArrayList<>();
        Applicant applicant = new Applicant();
        applicant.setApplicantId(User.builder().id(1L).build());
        applicant.setAverageMark(7);
        applicantList.add(applicant);
        Mockito.when(statementRepository.findByUser(any())).thenReturn(statement);
        Mockito.when(statementRepository.save(any())).thenReturn(statement);
        StatementMappingServiceImpl statementMappingService1 = PowerMockito.spy(statementMappingService);
        Whitebox.invokeMethod(statementMappingService1,"setBudgetPlaceFlag",applicantList);
        Mockito.verify(statementRepository, times(1)).save(any());
    }

    @Test
    public final void setNonBudgetPlaceFlagTest() throws Exception {
        Statement statement = Statement.builder().build();
        List<Applicant> applicantList = new ArrayList<>();
        Applicant applicant = new Applicant();
        applicant.setApplicantId(User.builder().id(1L).build());
        applicant.setAverageMark(7);
        applicantList.add(applicant);
        Mockito.when(statementRepository.findByUser(any())).thenReturn(statement);
        Mockito.when(statementRepository.save(any())).thenReturn(statement);
        StatementMappingServiceImpl statementMappingService1 = PowerMockito.spy(statementMappingService);
        Whitebox.invokeMethod(statementMappingService1,"setNonBudgetPlaceFlag",applicantList);
        Mockito.verify(statementRepository, times(1)).save(any());
    }
}
