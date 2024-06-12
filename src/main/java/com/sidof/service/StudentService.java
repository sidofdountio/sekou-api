package com.sidof.service;

import com.sidof.dto.StudentDto;
import com.sidof.dto.StudentRequest;
import com.sidof.model.Student;
import com.sidof.repo.StudentRepo;
import com.sidof.service.inplementation.StudentServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        var student = Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .dateOfBirth(studentRequest.getDateOfBirth())
                .build();
        log.info("Saving new student");
        return studentRepo.save(student);
    }

    @Override
    public Student update(StudentDto studentDto) throws BadRequestException {
        boolean existsStudent = studentRepo.existsById(studentDto.getId());
        if (!existsStudent) {
            log.error("Student id {} does exist", studentDto.getId());
            throw new BadRequestException("Student id: " + studentDto.getId() + " does exit");
        }
        var student = Student.builder()
                .id(studentDto.getId())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .email(studentDto.getEmail())
                .gender(studentDto.getGender())
                .address(studentDto.getAddress())
                .level(studentDto.getLevel())
                .currentGradeLevel(studentDto.getCurrentGradeLevel())
                .dateOfBirth(studentDto.getDateOfBirth())
                .option(studentDto.getOption())
                .imageUrl(studentDto.getImageUrl())
                .emergencyContact(studentDto.getEmergencyContact())
                .phoneNumber(studentDto.getPhoneNumber())
                .build();
        log.info("Updating student");
        return studentRepo.save(student);
    }

    @Override
    public Student getStudent(Long id) throws BadRequestException {
        log.info("Fetching student id {}", id);
        return studentRepo.findById(id).orElseThrow(() -> new BadRequestException(String.format("student id {} not found", id)));
    }
}
