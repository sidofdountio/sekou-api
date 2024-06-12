package com.sidof.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 08/06/2024  <br>
 * Version    : v1.0.0
 */
@Data
@Entity
@Table(name = "speciality")
@AllArgsConstructor
@NoArgsConstructor
public class Speciality {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "speciality_id_sequence")
    @SequenceGenerator(name = "speciality_id_sequence",allocationSize = 1,sequenceName = "speciality_id_sequence")
    private Long id;
    private String name;
    @OneToOne(mappedBy = "speciality")
    private Option option;

    public Speciality(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
