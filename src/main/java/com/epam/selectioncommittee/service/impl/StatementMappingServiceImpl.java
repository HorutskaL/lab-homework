package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.model.*;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.repository.StatementRepository;
import com.epam.selectioncommittee.service.StatementMappingService;
import com.epam.selectioncommittee.service.exception.FacultyNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StatementMappingServiceImpl implements StatementMappingService {
    private final StatementRepository statementRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public void finaliseStatement(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(FacultyNotFoundException::new);
        List<Statement> statements = faculty.getStatements();
        List<Applicant> applicants = new ArrayList<>();
        for (Statement statement : statements) {
            UserInfo userInfo = statement.getUser().getUserInfo();
            Applicant applicant = new Applicant();
            applicant.setApplicantId(userInfo.getUser());
            Subject subject = new Subject();
            subject.setUser(userInfo.getUser());
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
        applicants.sort((a, b) -> Double.compare(b.getAverageMark(), a.getAverageMark()));
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
                setBudgetPlaceFlag(applicants);
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
}
