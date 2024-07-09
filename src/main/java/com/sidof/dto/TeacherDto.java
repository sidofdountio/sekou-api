package com.sidof.dto;

import com.sidof.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder @AllArgsConstructor
public class TeacherDto {
    private String lastName;
    private String firstName;
    private String email;
    private int phone;

    public static Teacher convertTeacherDTOToTeacher(TeacherDto dto){
        return Teacher.builder().id(null) .lastName(dto.getLastName()).firstName(dto.getFirstName()).email(dto.getEmail()).phone(dto.getPhone()) .build();
    }

}