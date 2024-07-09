package com.sidof.repo;

import com.sidof.model.*;
import com.sidof.model.enumeration.AssessmentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AssessmentRepoTest {
    @Autowired
    private AssessmentRepo underTest;
    @Autowired
    private OptionRepo optionRepo;
    @Autowired
    private SpecialityRepo  specialityRepo;
    @Autowired
    private LevelRepo levelRepo;
    @Autowired
    private CourseRepo courseRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    @Disabled
    void findByOptionAndLevelAndYear() {
    }

    @Test
    void canFindByOptionAndLevelAndYearAndAssessmentType() {
//        GIVEN
//        When
        Course course = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .build();
        Course PROGRAMING = courseRepo.save(course);
        Speciality SOFTWARE = specialityRepo.save(new Speciality(1L, "SOFTWARE ENGINEER"));
        Option gsi = optionRepo.save(new Option(1L, "GSI", SOFTWARE));
        Level LEVEL1 = levelRepo.save(new Level(null, "Level 1"));
        Year year = Year.now();
        var assessment = Assessment.builder()
                .id(1L)
                .level(LEVEL1)
                .option(gsi)
                .date(LocalDate.of(2024,10,1))
                .due(LocalDate.of(2024,10,5))
                .assessmentType(AssessmentType.EXAM)
                .year(Year.now())
                .course(PROGRAMING)
                .build();
        underTest.save(assessment);
        List<Assessment> byOptionAndLevelAndYearAndAssessmentType = underTest.findByOptionAndLevelAndYearAndAssessmentType(
                assessment.getOption(),
                assessment.getLevel(),
                assessment.getYear(),
                assessment.getAssessmentType());
//        then
        System.out.println(byOptionAndLevelAndYearAndAssessmentType);
        assertNotNull(byOptionAndLevelAndYearAndAssessmentType);
    }
}