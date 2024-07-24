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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "teacher_id_sequence")
    @SequenceGenerator(name = "teacher_id_sequence", allocationSize = 1, sequenceName = "teacher_id_sequence")
    private Long id;
    private String lastName;
    private String firstName;
    @Column(unique = true)
    private String email;
    private int phone;
    @Column(nullable = true)
    private String grade;
    @Column(nullable = true)
    private String diploma;
    @Column(nullable = true)
    private int experience;
    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<CourseOffering> courseOfferingList = new ArrayList<>();


}
