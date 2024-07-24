package com.sidof.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Year;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Notice that attribute Year has different name on database properties called years
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_id_courseoffering")
    @SequenceGenerator(name = "sequence_id_courseoffering", allocationSize = 1, sequenceName = "sequence_id_courseoffering")
    private Long id;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;
    @Column(name = "years",nullable = false)
    private Year year;
    @ManyToOne
    @JoinColumn(name = "option_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_course_offering_option"))
    private Option option;
    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_course_offering_level"))
    private Level level;
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_course_offering_teacher"))
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_course_offering_course"))
    private Course course;


}
