package com.sidof.service;

import com.sidof.model.*;
import com.sidof.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CourseOfferingServiceTest {

    private CourseOfferingService underTest;
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

    @BeforeEach
    void setUp() {
        underTest= new CourseOfferingService(courseOfferingRepo);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void canFindByOptionAndLevelAndYear() {
        Course course = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .build();
        Course PROGRAMING = courseRepo.save(course);
        Speciality SOFTWARE = specialityRepo.save(new Speciality(1L, "SOFTWARE ENGINEER"));
        Option gsi = optionRepo.save(new Option(1L, "GSI", SOFTWARE));
        Level LEVEL1 = levelRepo.save(new Level(1L, "Level 1"));
        Teacher JAMES = teacherRepo.save(new Teacher(1L, "James", "Gosling", "james@gmai.com", 672299929));
        Year year = Year.now();
        LocalTime startTime = LocalTime.parse("14:00");
        LocalTime endTime = LocalTime.parse("16:00");
        courseOfferingRepo.save(new CourseOffering(1L,startTime,endTime,DayOfWeek.MONDAY,LEVEL1, gsi,PROGRAMING,JAMES, year));
        underTest.findByOptionAndLevelAndYear(gsi, LEVEL1, year);
    }

    @Test
    void canSaveNewCourseOffering() throws BadRequestException {
//        GIVEN
        Course course = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .build();
        Course PROGRAMING = courseRepo.save(course);
        Speciality SOFTWARE = specialityRepo.save(new Speciality(1L, "SOFTWARE ENGINEER"));
        Option gsi = optionRepo.save(new Option(1L, "GSI", SOFTWARE));
        Level LEVEL1 = levelRepo.save(new Level(null, "Level 1"));
        Teacher JAMES = teacherRepo.save(new Teacher(1L, "James", "Gosling", "james@gmai.com", 672299929));
        Year year = Year.now();
        LocalTime startTime = LocalTime.parse("14:00");
        LocalTime endTime = LocalTime.parse("16:00");
        CourseOffering courseOffering = new CourseOffering(1L, startTime, endTime, DayOfWeek.MONDAY, LEVEL1, gsi, PROGRAMING, JAMES, year);
        underTest.save(courseOffering);
//        WHEN
        ArgumentCaptor<CourseOffering> argumentCaptor = ArgumentCaptor.forClass(CourseOffering.class);
        verify(courseOfferingRepo).save(argumentCaptor.capture());
        CourseOffering argumentCaptorValue = argumentCaptor.getValue();
//        THEN
        assertEquals(argumentCaptorValue,courseOffering);
    }

    @Test
    void showErrorWhileSavingCourseOfferingIfStartTimeIsNull() throws BadRequestException {
//        GIVEN
        Course course = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .build();
        Course PROGRAMING = courseRepo.save(course);
        Speciality SOFTWARE = specialityRepo.save(new Speciality(1L, "SOFTWARE ENGINEER"));
        Option gsi = optionRepo.save(new Option(1L, "GSI", SOFTWARE));
        Level LEVEL1 = levelRepo.save(new Level(null, "Level 1"));
        Teacher JAMES = teacherRepo.save(new Teacher(1L, "James", "Gosling", "james@gmai.com", 672299929));
        Year year = Year.now();
        LocalTime startTime = LocalTime.parse("16:00");
        LocalTime endTime = LocalTime.parse("16:00");
        CourseOffering courseOffering = new CourseOffering(1L, null, endTime, DayOfWeek.MONDAY, LEVEL1, gsi, PROGRAMING, JAMES, year);
//        WHEN
//       given(courseOffering).willReturn(courseOffering);
        assertThatThrownBy(()-> underTest.save(courseOffering))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Cannot save. Please provide start time");

//        THEN
        verify(courseOfferingRepo,never()).save(any());
        log.info("END OF TEST showErrorWhileSavingCourseOfferingIfStartTimeIsNull()");
    }

    @Test
    void showErrorWhileSavingCourseOfferingIfStartTimeIsEqualToEndDate() throws BadRequestException {
//        GIVEN
        Course course = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .build();
        Course PROGRAMING = courseRepo.save(course);
        Speciality SOFTWARE = specialityRepo.save(new Speciality(1L, "SOFTWARE ENGINEER"));
        Option gsi = optionRepo.save(new Option(1L, "GSI", SOFTWARE));
        Level LEVEL1 = levelRepo.save(new Level(null, "Level 1"));
        Teacher JAMES = teacherRepo.save(new Teacher(1L, "James", "Gosling", "james@gmai.com", 672299929));
        Year year = Year.now();
        LocalTime startTime = LocalTime.parse("16:00");
        LocalTime endTime = LocalTime.parse("16:00");
        CourseOffering courseOffering = new CourseOffering(1L, startTime, endTime, DayOfWeek.MONDAY, LEVEL1, gsi, PROGRAMING, JAMES, year);
//        WHEN
        assertThatThrownBy(()-> underTest.save(courseOffering))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Cannot save. Please provide different time");
//        THEN
        verify(courseOfferingRepo,never()).save(any());
        log.info("END OF TEST showErrorWhileSavingCourseOfferingIfStartTimeIsEqualToEndDate()");
    }

    @Test
    void edite() {
    }
}