package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "course_id_sequence")
    @SequenceGenerator(name = "course_id_sequence", allocationSize = 1, sequenceName = "course_id_sequence")
    private Long id;
    @Column(unique = false)
    private String title;
    private int credit;
    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = LAZY)
    private List<CourseEnrollment> courseEnrollmentList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = LAZY)
    private List<Assessment> assessments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = LAZY)
    private List<CourseOffering> courseOfferings = new ArrayList<>();

    public Course(Long id, String title, int credit) {
        this.id = id;
        this.title = title;
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public List<CourseEnrollment> getCourseEnrollmentList() {
        return courseEnrollmentList;
    }

    public void setCourseEnrollmentList(List<CourseEnrollment> courseEnrollmentList) {
        this.courseEnrollmentList = courseEnrollmentList;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public List<CourseOffering> getCourseOfferings() {
        return courseOfferings;
    }

    public void setCourseOfferings(List<CourseOffering> courseOfferings) {
        this.courseOfferings = courseOfferings;
    }
}
