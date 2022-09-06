package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.model.*;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.repository.StatementRepository;
import com.epam.selectioncommittee.repository.UserRepository;
import com.epam.selectioncommittee.service.StatementService;
import com.epam.selectioncommittee.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StatementServiceImpl implements StatementService {

    private final StatementRepository statementRepository;
    private final UserRepository userRepository;
    public final FacultyRepository facultyRepository;

    @Override
    public void addApplicantToStatement(Long facultyId) {
        Faculty faculty = facultyRepository.getFacultyById(facultyId);
        Set<User> users = faculty.getUsers();
        users.stream()
                .filter(user -> !statementRepository.existByUser(user))
                .forEach(user -> statementRepository.save(Statement.builder().user(user).faculty(faculty).build()));
    }

    @Override
    public void removeApplicantFromStatement(Long userId) {
        User user = userRepository.findUserById(userId);
        Statement statement = statementRepository.findByUser(user);
        statementRepository.delete(statement);
    }

    @Override
    public void finaliseStatement(Long facultyId) {
        Faculty faculty = facultyRepository.getFacultyById(facultyId);
        List<Statement> statements = faculty.getStatements();
        List<Applicant> applicants = new ArrayList<>();
        for (Statement statement : statements) {
            UserInfo userInfo = statement.getUser().getUserInfo();
            Applicant applicant = new Applicant();
            applicant.setApplicantId(userInfo.getUserId());
            Subject subject = new Subject();
            subject.setUserId(userInfo.getUserId());
            applicant.setAverageMark(getAverageMark(subject, faculty));
            applicants.add(applicant);
        }
        finalise(applicants, faculty);
    }


    private double getAverageMark(Subject subject, Faculty faculty) {
        double sumMark = subject.getAlgebra() + subject.getGeography() + subject.getInformatics() +
                subject.getPhTraining() + subject.getGeometry() + subject.getScience() +
                subject.getUkHistory() + subject.getUkLanguage() + subject.getUkLiterature() +
                subject.getForeignLanguage();
        double divisor = 10;
        if (faculty.getIsEieHistory() == 1) {
            sumMark += subject.getEieHistory();
            ++divisor;
        }
        if (faculty.getIsEieMath() == 1) {
            sumMark += subject.getEieMath();
            ++divisor;
        }
        if (faculty.getIsEieUkLanguage() == 1) {
            sumMark += subject.getEieUkLanguage();
            ++divisor;
        }
        return sumMark / divisor;
    }

    private void finalise(List<Applicant> applicants, Faculty faculty) {
        applicants.sort((a, b) -> ((Double) b.getAverageMark()).compareTo((Double) a.getAverageMark()));

        if (applicants.size() >= faculty.getAmountTotalPlaces()) {
            List<Applicant> applicantListBudgetPl = applicants.subList(0, faculty.getAmountBudgetPlaces());
            List<Applicant> applicantListNonBudgetPl = applicants.subList(faculty.getAmountBudgetPlaces(), faculty.getAmountTotalPlaces());
            setBudgetPlaceFlag(applicantListBudgetPl);
            setNonBudgetPlaceFlag(applicantListNonBudgetPl);
        }
        if (applicants.size() <= faculty.getAmountTotalPlaces()) {
            if (applicants.size() >= faculty.getAmountBudgetPlaces()) {
                List<Applicant> applicantListBudgetPl = applicants.subList(0, faculty.getAmountBudgetPlaces());
                setBudgetPlaceFlag(applicantListBudgetPl);
            }
            if (applicants.size() <= faculty.getAmountBudgetPlaces()) {
                List<Applicant> applicantListBudgetPl = applicants;
                setBudgetPlaceFlag(applicantListBudgetPl);
            }
            if (applicants.size() >= faculty.getAmountBudgetPlaces()) {
                List<Applicant> applicantListBudgetPl = applicants.subList(faculty.getAmountBudgetPlaces(), applicants.size());
                setBudgetPlaceFlag(applicantListBudgetPl);
            }
        }

    }

    private void setBudgetPlaceFlag(List<Applicant> applicantListBudgetPl) {
        for (Applicant applicant : applicantListBudgetPl) {
            Statement statement = statementRepository.findByUser(applicant.getApplicantId());
            statement.setAmountBudgetPl(1);
            statement.setAmountNonBudgetPl(0);
            statementRepository.save(statement);
        }
    }

    private void setNonBudgetPlaceFlag(List<Applicant> applicantListNonBudgetPl) {
        for (Applicant applicant : applicantListNonBudgetPl) {
            Statement statement = statementRepository.findByUser(applicant.getApplicantId());
            statement.setAmountBudgetPl(0);
            statement.setAmountNonBudgetPl(1);
            statementRepository.save(statement);
        }
    }


    @Override
    public List<UserDto> getApplicantList(Long facultyId) {
        Faculty faculty = facultyRepository.getFacultyById(facultyId);
        List<Statement> applicantList = statementRepository.findByFaculty(faculty);
        return applicantList.stream()
                .map(statement -> UserMapper.INSTANCE.mapUserToUserDto(statement.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getBudgetPlApplicantList(Long facultyId) {
        Faculty faculty = facultyRepository.getFacultyById(facultyId);
        List<Statement> applicantList = statementRepository.findBudgetStPlByFaculty(faculty);
        return applicantList.stream()
                .map(statement -> UserMapper.INSTANCE.mapUserToUserDto(statement.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getNonBudgetPlApplicantList(Long facultyId) {
        Faculty faculty = facultyRepository.getFacultyById(facultyId);
        List<Statement> applicantList = statementRepository.findNonBudStPlByFaculty(faculty);
        return applicantList.stream()
                .map(statement -> UserMapper.INSTANCE.mapUserToUserDto(statement.getUser()))
                .collect(Collectors.toList());
    }
}
