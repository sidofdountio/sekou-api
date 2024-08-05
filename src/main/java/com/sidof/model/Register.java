package com.sidof.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.time.Year;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 *
 * @Param startDate and endDate represent the school year period.
 * @Param endDate Represent The current year period school. To get correctly value we get a value from endYear to User.
 * After that we make minus 1 to the value of ent starting Year. It should look like this 2023-2024
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
/**
 * Use this class to perform registration of student for each year school.
 */
public class Register {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "register_id_sequence")
    @SequenceGenerator(name = "register_id_sequence",
            allocationSize = 1,
            sequenceName = "register_id_sequence")
    private Long id;
    private LocalDate registerDate;
    private Year startDate;
    private Year endDate;
    private double feeRegister;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_register_student"))
    private Student student;
    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_register_level"))
    private Level level;
    @ManyToOne
    @JoinColumn(name = "option_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_register_option"))
    private Option option;

//    TODO: we will create an class feeTuition. To save student tuition
}
