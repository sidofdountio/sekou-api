package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Author     : sidof <br>
 * LinkedIn   :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since      : 05/06/2024  <br>
 * Version    : v1.0.0
 */

/**
 * Option represent that student chooses to study.
 * Like GSI, TP, BAT, RH, GMH.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "option_id_sequence")
    @SequenceGenerator(name = "option_id_sequence",allocationSize = 1,sequenceName = "option_id_sequence")
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    private Speciality speciality;
    @JsonIgnore
    @OneToMany(mappedBy = "option")
    private List<CourseEnrollment> courseEnrollmentList=new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",fetch = LAZY)
    private List<Student> student=new ArrayList<>();

    public Option(Long id, String name, Speciality speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }
}
