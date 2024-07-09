package com.sidof.model;

import com.sidof.model.enumeration.AssessmentType;
import com.sidof.model.enumeration.StudentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudentAssessment {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "student_assessment_id_sequence")
    @SequenceGenerator(name = "student_assessment_id_sequence", allocationSize = 1, sequenceName = "student_assessment_id_sequence")
    private Long id;
    @Column(nullable = false)
    private int score;
    private String feedback;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_assessment_student"))
    private Student student;
    @Column(nullable = false,name = "years")
    private Year year;
    @Enumerated(STRING)
    private AssessmentType assessmentType;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "level_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_assessment_level"))
    private Level level;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "option_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_assessment_option"))
    private Option option;
    @Enumerated(STRING)
    private StudentStatus studentStatus;
        @ManyToOne(cascade = ALL)
    @JoinColumn(name = "assessment_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_assessment_assessment"))
    private Assessment assessment;


}
