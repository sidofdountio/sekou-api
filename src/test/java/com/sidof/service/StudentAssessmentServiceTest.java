package com.sidof.service;

import com.sidof.repo.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class StudentAssessmentServiceTest {
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
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
    }

    @Test
    void getStudentAssessment() {
    }

    @Test
    void edit() {
    }

    @Test
    void getStudentAssessmentList() {
    }

    @Test
    void findByOptionAndLevelAndYearAndAssessmentType() {
    }
}