package com.sidof.dto;

import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.Register;
import com.sidof.model.Student;
import com.sidof.model.enumeration.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
@Data
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(STRING)
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private String address;
    private String emergencyContact;
    private String currentGradeLevel;
    private String imageUrl;
    private Level level;
    private Option option;

    public Student convertDtoToStudent(StudentDto studentDto) {
        return Student.builder()
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
    }


}
