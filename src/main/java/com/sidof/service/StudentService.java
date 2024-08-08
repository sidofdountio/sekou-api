package com.sidof.service;

import com.sidof.dto.StudentDto;
import com.sidof.dto.StudentRequest;
import com.sidof.model.Student;
import com.sidof.model.enumeration.SchoolPayStatus;
import com.sidof.repo.StudentRepo;
import com.sidof.service.inplementation.StudentServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import static com.sidof.model.enumeration.SchoolPayStatus.*;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StudentService implements StudentServiceImpl {

    private final StudentRepo studentRepo;

    @Override
    public List<Student> getStudents() {
        log.info("Fetching students");
        return studentRepo.findAll();
    }

    @Override
    public Student save(StudentRequest studentRequest) throws BadRequestException {
        Optional<Student> studentRepoByEmail = studentRepo.findByEmail(studentRequest.getEmail());
        if (studentRepoByEmail.isPresent()) {
            log.error("Email {} provide it taken", studentRequest.getEmail());
            throw new BadRequestException("Email" + studentRequest.getEmail() + "provide it taken");
        }
        LocalDate dateOfBirth = studentRequest.getDateOfBirth();
        int studegeAge = LocalDate.now().minusYears(dateOfBirth.getYear()).getYear();
        var student = Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .dateOfBirth(studentRequest.getDateOfBirth())
                .age(studegeAge)
                .schoolPayStatus(NOPAY)
                .build();
        log.info("Saving new student");
        return studentRepo.save(student);
    }

    @Override
    public Student update(Student student) throws BadRequestException {
        boolean existsStudent = studentRepo.existsById(student.getId());
        if (!existsStudent) {
            log.error("Student id {} does exist", student.getId());
            throw new BadRequestException("Student id: " + student.getId() + " does exit");
        }
        log.info("Updating student");
        LocalDate dateOfBirth = student.getDateOfBirth();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        int age = period.getYears();
        student.setAge(age);
        return studentRepo.save(student);
    }

    @Override
    public Student getStudent(Long id) throws BadRequestException {
        log.info("Fetching student id {}", id);
        return studentRepo.findById(id).orElseThrow(() -> new BadRequestException(String.format("student id {} not found", id)));
    }
}
