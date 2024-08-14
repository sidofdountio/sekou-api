package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sidof.model.enumeration.Gender;
import com.sidof.model.enumeration.SchoolPayStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 05/06/2024  <br>
 * Version    : v1.0.0
 */

/**
 * Student Class represent a single Student object with few attributes.
 * @Param Level object or attribute  describe  grade like Level 1, Level 2
 * @Param Option attribute describe student course, What he do like GSI, CGE, TP, RH
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "student_id_sequence")
    @SequenceGenerator(name = "student_id_sequence",allocationSize = 1,sequenceName = "student_id_sequence")
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private LocalDate dateOfBirth;
    @Enumerated(STRING)
    private Gender gender;
    @Column(unique = true,nullable = false)
    private String email;
    private String phoneNumber;
    private String address;
    private String emergencyContact;
    private String currentGradeLevel;
    @Column(nullable = true)
    private int age;
    @Column(nullable = true)
    private String imageUrl;
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Register> registerList=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "level_id", nullable = true,referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_level"))
    private Level level;
    @ManyToOne
    @JoinColumn(name = "option_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_option"))
    private Option option;
    @JsonIgnore
    @OneToMany(mappedBy ="student")
    private List <StudentAssessment> studentAssessments=new ArrayList<>();
    private boolean registed;
    @Enumerated(STRING)
    private SchoolPayStatus schoolPayStatus;

    public Student(Long id, String firstName, String lastName, Gender gender, LocalDate dateOfBirth, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }
}
