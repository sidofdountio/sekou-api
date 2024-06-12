package com.sidof.repo;

import com.sidof.model.Course;
import com.sidof.model.Option;
import com.sidof.model.Speciality;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CourseRepoTest {
    @Autowired
    private  CourseRepo underTest;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByTitle() {
//        given
        Course PROGRAMING = Course.builder()
                .title("Java")
                .credit(4)
                .id(1L)
                .courseEnrollmentList(new ArrayList<>())
                .build();
//        when
        underTest.save(PROGRAMING);
        Optional<Course> expected = underTest.findByTitle(PROGRAMING.getTitle());
//        then
        assertEquals(Optional.of(PROGRAMING),expected);
    }
}