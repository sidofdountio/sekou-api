package com.sidof.service;

import com.sidof.dto.StudentDto;
import com.sidof.dto.StudentRequest;
import com.sidof.model.CourseEnrollment;
import com.sidof.model.Student;
import com.sidof.repo.StudentRepo;
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

import java.util.List;
import java.util.Optional;

import static com.sidof.model.enumeration.Gender.MALE;
import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private StudentService underTest;
    @Mock
    private StudentRepo studentRepo;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepo);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void canGetAllStudents() {
        //        given
        var studentRequest = StudentRequest.builder()
                .firstName("James")
                .lastName("Golsing")
                .email("jamesgolsing@gmail.com")
                .gender(MALE)
                .dateOfBirth(of(1985, 10, 12))
                .build();
        var student = Student.builder()
                .firstName("James")
                .lastName("Golsing")
                .email("jamesgolsing@gmail.com")
                .gender(MALE)
                .dateOfBirth(of(1985, 10, 12))
                .build();
        List<Student> studentList = List.of(student);
//        when
        when(studentRepo.findAll()).thenReturn(studentList);

//        then
        assertEquals(1, studentList.size());
        for (Student student1 : studentList) {
            System.out.println(student1);
        }
        underTest.getStudents();
        verify(studentRepo).findAll();
    }

    @Test
    void canSaveNewStudent() throws BadRequestException {
        //        given
        var studentRequest = StudentRequest.builder()
                .firstName("James")
                .lastName("Golsing")
                .email("jamesgolsing@gmail.com")
                .gender(MALE)
                .dateOfBirth(of(1985, 10, 12))
                .build();
        var student = Student.builder()
                .firstName("James")
                .lastName("Golsing")
                .email("jamesgolsing@gmail.com")
                .gender(MALE)
                .dateOfBirth(of(1985, 10, 12))
                .build();
        underTest.save(studentRequest);
//        when
        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepo).save(argumentCaptor.capture());
        Student argumentCaptorValue = argumentCaptor.getValue();
        assertEquals(student, argumentCaptorValue);
        log.info("TEST save STUDENT PASSED");
    }

    @Test
    void canUpdateStudent() throws BadRequestException {
        //        given
        StudentDto studentDto = new StudentDto();
        studentDto.setEmail("jamesgolsing@gmail.com");
        studentDto.setId(1L);
        studentDto.setFirstName("James");
        studentDto.setLastName("Golsing");
        studentDto.setGender(MALE);
        studentDto.setDateOfBirth(of(1985, 10, 12));
        given(studentRepo.existsById(studentDto.getId())).willReturn(true);
        Student t = new Student(1L, "James", "Golsing", of(1985, 10, 12), MALE, "jamesgolsing@gmail.com");
        when(underTest.update(studentDto)).thenReturn(t);
        Student update = underTest.update(studentDto);
        System.out.println(update);
        studentDto.setAddress("Ontario, Canada");
        studentDto.setImageUrl("http://localhost:/v1/secou/sudent/image-url.png");
        assertNotEquals(studentDto.getAddress(), update.getAddress());
        log.info("TEST UPDATE STUDENT PASSED");

    }

    @Test
    void getStudent() throws BadRequestException {
//        given
        StudentDto studentDto = new StudentDto();
        studentDto.setEmail("jamesgolsing@gmail.com");
        studentDto.setId(1L);
        Student student = studentDto.convertDtoToStudent(studentDto);
        given(studentRepo.findById(studentDto.getId())).willReturn(Optional.of(student));
//        when
        underTest.getStudent(studentDto.getId());
//        then
        verify(studentRepo).findById(any());
    }
}