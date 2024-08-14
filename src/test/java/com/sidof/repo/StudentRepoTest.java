package com.sidof.repo;

import com.sidof.model.Option;
import com.sidof.model.Speciality;
import com.sidof.model.Student;
import com.sidof.model.enumeration.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepoTest {
    @Autowired
    private StudentRepo underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenStudentEmailExists() {

        String studentEmail = "john@gmail.com";
        var John = new Student(
                1L,
                "john",
                "doe",
                Gender.MALE,
                of(2000, 1, 1),
                studentEmail);
        underTest.save(John);
        Boolean expected = underTest.selectExistsStudentEmail(studentEmail);
        assertThat(expected).isTrue();

    }
    @Test
    void itShouldCheckWhenStudentDoesNotEmailExists() {

        String studentEmail = "john@gmail.com";

        Boolean expected = underTest.selectExistsStudentEmail(studentEmail);
        assertThat(expected).isFalse();

    }
}