package com.sidof.repo;

import com.sidof.dto.TeacherDto;
import com.sidof.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Year;
import java.util.List;

@DataJpaTest
class CourseOfferingRepoTest {
    @Autowired
    private CourseOfferingRepo underTest;
    @Autowired
    private SpecialityRepo specialityRepo;
    @Autowired
    private LevelRepo levelRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private OptionRepo optionRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }


    @Test
    @DisplayName("canFindByOptionLevelAndYear")
    void canFindByOptionLevelAndYear() {
        // GIVEN
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
        underTest.save(new CourseOffering(1L,startTime,endTime,DayOfWeek.MONDAY,LEVEL1, gsi,PROGRAMING,JAMES, year));
        List<CourseOffering> byOptionAndLevelAndYear = underTest.findByOptionAndLevelAndYear(gsi, LEVEL1, year);
        System.out.println(byOptionAndLevelAndYear);
    }
}