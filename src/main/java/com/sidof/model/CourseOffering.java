package com.sidof.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Entity
@Builder
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_course_offering")
    @SequenceGenerator(name = "sequence_course_offering", allocationSize = 1, sequenceName = "sequence_course_offering")
    private Long id;
    @Column(nullable = false)
    private LocalTime starTime;
    @Column(nullable = false)
    private LocalTime endTime;
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_course_offering_level"))
    private Level level;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "option_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_course_offering_option"))
    private Option option;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_course_offering_course"))
    private Course course;
        @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_course_offering_teacher"))
    private Teacher teacher;
    @Column(name = "years",nullable = false)
    private Year year;

}
