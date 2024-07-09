package com.sidof.service;

import com.sidof.model.*;
import com.sidof.model.enumeration.AssessmentType;
import com.sidof.repo.*;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceTest {
    private AssessmentService underTest;
    @Mock
    private CourseOfferingRepo courseOfferingRepo;
    @Mock
    private SpecialityRepo specialityRepo;
    @Mock
    private LevelRepo levelRepo;
    @Mock
    private TeacherRepo teacherRepo;
    @Mock
    private CourseRepo courseRepo;
    @Mock
    private OptionRepo optionRepo;
    @Mock
    private AssessmentRepo assessmentRepo;

    @BeforeEach
    void setUp() {
        underTest = new AssessmentService(assessmentRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void canSaveNewAssessment() throws BadRequestException {
//        Given
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
                .date(LocalDate.of(2024, 10, 1))
                .due(LocalDate.of(2024, 10, 5))
                .assessmentType(AssessmentType.EXAM)
                .year(Year.now())
                .course(PROGRAMING)
                .build();
        underTest.save(assessment);
//        when
        ArgumentCaptor<Assessment> argumentCaptor = ArgumentCaptor.forClass(Assessment.class);
        verify(assessmentRepo).save(argumentCaptor.capture());
        Assessment argumentCaptorValue = argumentCaptor.getValue();
//        then
        assertEquals(assessment, argumentCaptorValue);
    }

    @Test
    @Disabled
    void edit() {
    }

    @Test
    void getAssessmentList() {
        underTest.getAssessmentList();
        verify(assessmentRepo).findAll();
    }

    @Test
    void getAssessment() throws BadRequestException {
        //        Given
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
                .date(LocalDate.of(2024, 10, 1))
                .due(LocalDate.of(2024, 10, 5))
                .assessmentType(AssessmentType.EXAM)
                .year(Year.now())
                .course(PROGRAMING)
                .build();
        underTest.save(assessment);
//        when
        given(assessmentRepo.findById(1L)).willReturn(Optional.of(assessment));
        underTest.getAssessment(assessment.getId());
        verify(assessmentRepo).findById(any());
    }

    @Test
    @Disabled
    void findByOptionAndLevelAndYear() {
    }

    @Test
    void canFindByOptionAndLevelAndYearAndAssessmentType(){
//        Given
//        when
        //        Given
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
                .date(LocalDate.of(2024, 10, 1))
                .due(LocalDate.of(2024, 10, 5))
                .assessmentType(AssessmentType.EXAM)
                .year(Year.now())
                .course(PROGRAMING)
                .build();
        assessmentRepo.save(assessment);

        List<Assessment> byOptionAndLevelAndYearAndAssessmentType = underTest.findByOptionAndLevelAndYearAndAssessmentType(assessment.getOption(),
                assessment.getLevel(),
                assessment.getYear(),
                AssessmentType.ALL);
//        Then
        assertNotNull(byOptionAndLevelAndYearAndAssessmentType);
    }
}