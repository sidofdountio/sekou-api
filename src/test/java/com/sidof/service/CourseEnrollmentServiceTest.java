package com.sidof.service;

import com.sidof.model.*;
import com.sidof.repo.CourseEnrollmentRepo;
import com.sidof.repo.CourseRepo;
import com.sidof.repo.LevelRepo;
import com.sidof.repo.OptionRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CourseEnrollmentServiceTest {
    @Autowired
    private CourseEnrollmentService underTest;
    @Mock
    private CourseEnrollmentRepo courseEnrollmentRepo;
    @Mock
    private CourseRepo courseRepo;
    @Mock
    private LevelRepo levelRepo;
    @Mock
    private OptionRepo optionRepo;

    @BeforeEach
    void setUp() {
        underTest = new CourseEnrollmentService(courseEnrollmentRepo, courseRepo, levelRepo, optionRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void canGetOneEnrolledCourse() {
    }

    @Test
    void canEnrollNewCourse() throws BadRequestException {
//        given
        Course PROGRAMING = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .courseEnrollmentList(new ArrayList<>())
                .build();
        given(courseRepo.existsById(PROGRAMING.getId())).willReturn(true);
        Speciality SOFTWARE = new Speciality(1L, "SOFTWARE ENGINEER", new Option());
        Option GSI = new Option(1L, "GSI", SOFTWARE, new ArrayList<>(), new ArrayList<>());
//        Option GSI = new Option(1L, "GSI", SOFTWARE);
        given(optionRepo.existsById(GSI.getId())).willReturn(true);
        Level LEVEL1 = new Level(1L, "LEVEL 1");
        given(levelRepo.existsById(LEVEL1.getId())).willReturn(true);

        CourseEnrollment courseEnrollment = new CourseEnrollment(1L, GSI, PROGRAMING, LEVEL1);

        underTest.save(courseEnrollment);
//        when
        ArgumentCaptor<CourseEnrollment> argumentCaptor = ArgumentCaptor.forClass(CourseEnrollment.class);
        verify(courseEnrollmentRepo).save(argumentCaptor.capture());
        CourseEnrollment argumentCaptorValue = argumentCaptor.getValue();
        assertEquals(courseEnrollment, argumentCaptorValue);
        log.info("TEST ENROLLED COURSE PASSED");

    }

    @Test
    void shouldThrowErrorWhenEnroll() throws BadRequestException {
//        given
        Course PROGRAMING = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .courseEnrollmentList(new ArrayList<>())
                .build();
        given(courseRepo.existsById(PROGRAMING.getId())).willReturn(false);
        Speciality SOFTWARE = new Speciality(1L, "SOFTWARE ENGINEER", new Option());
        Option GSI = new Option(1L, "GSI", SOFTWARE, new ArrayList<>(), new ArrayList<>());
//        Option GSI = new Option(1L, "GSI", SOFTWARE);
//        given(optionRepo.existsById(GSI.getId())).willReturn(false);
        Level LEVEL1 = new Level(1L, "LEVEL 1");
//        given(levelRepo.existsById(LEVEL1.getId())).willReturn(true);
        CourseEnrollment courseEnrollment = new CourseEnrollment(1L, GSI, PROGRAMING, LEVEL1);

//        when
        assertThatThrownBy(() -> underTest.save(courseEnrollment))
                .hasMessageContaining("Course with id " + PROGRAMING.getId() + " does exist")
                .isInstanceOf(BadRequestException.class);
//        assertThatThrownBy(() -> underTest.save(courseEnrollment))
//                .hasMessageContaining("Level with id " + LEVEL1.getId() + "does exist")
//                .isInstanceOf(BadRequestException.class);
//        assertThatThrownBy(() -> underTest.save(courseEnrollment))
//                .hasMessageContaining("Option with id " + GSI.getId() + "does exist")
//                .isInstanceOf(BadRequestException.class);
        verify(courseEnrollmentRepo, never()).save(any());
        log.info("TEST THROW WHEN ENROLLED COURSE PASSED");

    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    void canGetAllCourseEnrollment() throws BadRequestException {
        //        given
        Course PROGRAMING = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .courseEnrollmentList(new ArrayList<>())
                .build();
        Speciality SOFTWARE = new Speciality(1L, "SOFTWARE ENGINEER", new Option());
        Option GSI = new Option(1L, "GSI", SOFTWARE, new ArrayList<>(), new ArrayList<>());
//        Option GSI = new Option(1L, "GSI", SOFTWARE);

        Level LEVEL1 = new Level(1L, "LEVEL 1");

        CourseEnrollment courseEnrollment = new CourseEnrollment(1L, GSI, PROGRAMING, LEVEL1);
        List<CourseEnrollment> courseEnrollmentList = List.of(courseEnrollment);
//        when
        when(courseEnrollmentRepo.findAll()).thenReturn(courseEnrollmentList);
//        then
        assertEquals(1, courseEnrollmentList.size());
        for (CourseEnrollment courseEnrollment1 : courseEnrollmentList) {
            System.out.println(courseEnrollment1);
        }
        underTest.getEnrollments();
        verify(courseEnrollmentRepo).findAll();
    }
}