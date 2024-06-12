package com.sidof.service;

import com.sidof.model.Course;
import com.sidof.repo.CourseRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    private CourseService underTest;
    @Mock
    private CourseRepo courseRepo;

    @BeforeEach
    void setUp() {
        underTest = new CourseService(courseRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void canSaveCourse() throws BadRequestException {
        //        given
        Course PROGRAMING = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .courseEnrollmentList(new ArrayList<>())
                .build();
//        when
        underTest.save(PROGRAMING);
        ArgumentCaptor<Course> argumentCaptor = ArgumentCaptor.forClass(Course.class);
        verify(courseRepo).save(argumentCaptor.capture());
        Course argumentCaptorValue = argumentCaptor.getValue();
//        then
        assertEquals(PROGRAMING, argumentCaptorValue);
        log.warn("TEST ADD NEW COURSE PASSED");
    }

    @Test
    void shouldShowErrorWhenCourseExist() throws BadRequestException {
        //        given
        Course PROGRAMING = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .courseEnrollmentList(new ArrayList<>())
                .build();
//        when
        given(courseRepo.findByTitle(PROGRAMING.getTitle())).willReturn(Optional.of(PROGRAMING));
        Assertions.assertThatThrownBy(() -> underTest.save(PROGRAMING))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Course with this title " + PROGRAMING.getTitle() + " exist");
//then
        verify(courseRepo, never()).save(any());

    }

    @Test
    void canUpdateCourse() throws BadRequestException {
        //        given
        Course PROGRAMING = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .courseEnrollmentList(new ArrayList<>())
                .build();
//        when
        when(underTest.update(PROGRAMING)).thenReturn(PROGRAMING);
        PROGRAMING.setCredit(3);
        Course update = underTest.update(PROGRAMING);
        int expected = update.getCredit();
//        then
        assertEquals(expected, PROGRAMING.getCredit());
        log.info("expected {}", expected);
        log.error("TEST UPDATE PASSED");

    }

    @Test
    @Disabled
    void getCourse() {
    }

    @Test
    void canGetAllCourse() {
//        given
//        when
        underTest.getCourses();
//        then
        verify(courseRepo).findAll();
    }
}