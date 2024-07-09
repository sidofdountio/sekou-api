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
    @JoinColumn(name = "speciality_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_option_speciality"))
    private Speciality speciality;
    @JsonIgnore
    @OneToMany(mappedBy = "option",fetch = LAZY)
    private List<CourseEnrollment> courseEnrollmentList=new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",fetch = LAZY)
    private List<Student> student=new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",fetch = LAZY)
    private List<Assessment> assessments=new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",fetch = LAZY)
    private List<CourseOffering> courseOfferings=new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "option",fetch = LAZY)
    private List<StudentAssessment> studentAssessments=new ArrayList<>();

    public Option(Long id, String name, Speciality speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }

    public Option(Long id, String name, Speciality speciality, List<CourseEnrollment> courseEnrollmentList, List<Student> student) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.courseEnrollmentList = courseEnrollmentList;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<CourseEnrollment> getCourseEnrollmentList() {
        return courseEnrollmentList;
    }

    public void setCourseEnrollmentList(List<CourseEnrollment> courseEnrollmentList) {
        this.courseEnrollmentList = courseEnrollmentList;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public List<CourseOffering> getCourseOfferings() {
        return courseOfferings;
    }

    public void setCourseOfferings(List<CourseOffering> courseOfferings) {
        this.courseOfferings = courseOfferings;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public List<StudentAssessment> getStudentAssessments() {
        return studentAssessments;
    }

    public void setStudentAssessments(List<StudentAssessment> studentAssessments) {
        this.studentAssessments = studentAssessments;
    }
}
