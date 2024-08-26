package com.sidof.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.time.Year;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 23/08/2024  <br>
 * Version    : v1.0.0
 */


/**
 * This class will be used to create each school year.
 * For each new school theses previous will be desactive.
 */
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "school_sequence")
    @SequenceGenerator(name = "school_sequence", allocationSize = 1, sequenceName = "school_sequence")
    private Long id;
    private Year schoolYear;
    private Year years;
    private String schoolName;
    private String shortName;
    private boolean active;


    public School() {
    }

    public School(Long id, Year schoolYear, String schoolName, String shortName, boolean active) {
        this.id = id;
        this.schoolYear = schoolYear;
        this.schoolName = schoolName;
        this.shortName = shortName;
        this.active = active;
    }

    public Year getYears() {
        return years;
    }

    public void setYears(Year years) {
        this.years = years;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Year getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Year schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolYear=" + schoolYear +
                ", years=" + years +
                ", schoolName='" + schoolName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", active=" + active +
                '}';
    }
}
