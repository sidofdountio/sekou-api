package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sidof.model.enumeration.AssessmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assessment {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "assessment_id_sequence")
    @SequenceGenerator(name = "assessment_id_sequence", allocationSize = 1, sequenceName = "assessment_id_sequence")
    private Long id;
    @Enumerated(STRING)
    private AssessmentType assessmentType;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalDate due;
    @Column(nullable = false,name = "years")
    private Year year;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "level_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_assessment_level"))
    private Level level;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "option_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_assessment_option"))
    private Option option;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_assessment_course"))
    private Course course;
    @JsonIgnore
    @OneToMany(mappedBy = "assessment")
    private List<StudentAssessment> studentAssessments = new ArrayList<>();

}
