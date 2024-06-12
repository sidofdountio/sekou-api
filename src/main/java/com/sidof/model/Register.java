package com.sidof.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.time.Year;

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
public class Register {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "register_id_sequence")
    @SequenceGenerator(name = "register_id_sequence",
            allocationSize = 1,
            sequenceName = "register_id_sequence")
    private Long id;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDate registerDate;
    private Year startDate;
    private Year endDate;
    @NumberFormat(pattern = "#,###.00")
    private double feeRegister;
//    private double feeTuition;
    @ManyToOne
    @JoinColumn(name="student_id",referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_register_student"))
    private Student student;
//    TODO: we will create an class feeTuition. To save student tuition
}
