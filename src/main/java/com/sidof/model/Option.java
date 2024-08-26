package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;


/**
 * Author     : sidof <br>
 * LinkedIn   :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since      : 05/06/2024  <br>
 * Version    : v1.0.0
 * Like GSI, TP, BAT, RH, GMH.
 * Option represent that student chooses to study.
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "option_id_sequence")
    @SequenceGenerator(name = "option_id_sequence", allocationSize = 1, sequenceName = "option_id_sequence")
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String fullName;
    @OneToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_option_speciality"))
    private Speciality speciality;
    @JsonIgnore
    @OneToMany(mappedBy = "option",cascade = CascadeType.ALL)
    private List<CourseEnrollment> courseEnrollmentList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",cascade = CascadeType.ALL)
    private List<Student> student = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",cascade = CascadeType.ALL)
    private List<Assessment> assessments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",cascade = CascadeType.ALL)
    private List<StudentAssessment> studentAssessments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",cascade = CascadeType.ALL)
    private List<CourseOffering> courseOfferingList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",cascade = CascadeType.ALL)
    private List<Register> registers = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",cascade = CascadeType.ALL)
    private List<SchoolFee> schoolFees = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",cascade = CascadeType.ALL)
    private List<StudentSchoolFee> studentSchoolFees = new ArrayList<>();

    public Option(Long id, String name, Speciality speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }

    public Option(String name, String fullName, Speciality speciality) {
        this.name = name;
        this.fullName = fullName;
        this.speciality = speciality;
    }
}
