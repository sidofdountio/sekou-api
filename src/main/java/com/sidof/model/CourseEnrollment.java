package com.sidof.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
public class CourseEnrollment {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "enrollment_id_sequence")
    @SequenceGenerator(name = "enrollment_id_sequence", allocationSize = 1, sequenceName = "enrollment_id_sequence")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "option_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_course_enrollment_option"))
    private Option option;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_course_enrollment_courses"))
    private Course course;
    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_course_enrollment_level"))
    private Level level;
}
