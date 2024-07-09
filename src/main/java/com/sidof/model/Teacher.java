package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "teacher_id_sequence")
    @SequenceGenerator(name = "teacher_id_sequence", allocationSize = 1, sequenceName = "teacher_id_sequence")
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private int phone;
    private String grade;
    private String diploma;
    private int experience;
    @JsonIgnore
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<CourseOffering> courseOfferings=new ArrayList<>();

    public Teacher(Long id, String lastName, String firstName, String email, int phone) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
    }
}
