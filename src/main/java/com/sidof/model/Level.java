package com.sidof.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 05/06/2024  <br>
 * Version    : v1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "level_id_sequence")
    @SequenceGenerator(name = "level_id_sequence",allocationSize = 1,sequenceName = "level_id_sequence")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "level",fetch = LAZY)
    private List<CourseEnrollment> courseEnrollmentList=new ArrayList<>();
    @OneToMany(mappedBy = "level",fetch = LAZY)
    private List<Student> student=new ArrayList<>();

    public Level(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
