package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sidof.model.enumeration.AssessmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 05/06/2024  <br>
 * Version    : v1.0.0
 */

/**
 * This class could be used to scheduler student assessment.
 */
@Data
@Entity
@Table(name = "assessment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assessment {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "assessment_id_sequence")
    @SequenceGenerator(name = "assessment_id_sequence", allocationSize = 1, sequenceName = "assessment_id_sequence")
    private Long id;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;
    @Column(nullable = false)
    @Enumerated(STRING)
    private AssessmentType assessmentType;
    @Column(nullable = false,name = "years")
    private Year year;
    @ManyToOne
    @JoinColumn(name = "assessment_Period_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_assessment_assessment_period"))
    private AssessmentPeriod assessmentPeriod;
    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_assessment_level"))
    private Level level;
    @ManyToOne
    @JoinColumn(name = "option_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_assessment_option"))
    private Option option;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_assessment_course"))
    private Course course;
    @JsonIgnore
    @OneToMany(mappedBy = "assessment")
    private List<StudentAssessment> studentAssessments = new ArrayList<>();

}
