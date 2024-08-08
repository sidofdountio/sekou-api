package com.sidof.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/08/2024  <br>
 * Version    : v1.0.0
 */

@Entity
public class SchoolFee {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "school_fee_id_sequence")
    @SequenceGenerator(name = "school_fee_id_sequence", allocationSize = 1, sequenceName = "school_fee_id_sequence")
    private Long id;
    private double totalFee;
    private double registerFee;
    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_school_fee_level"))
    private Level level;
    @ManyToOne
    @JoinColumn(name = "option_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_school_fee_option"))
    private Option option;

    public SchoolFee(Long id, double totalFee, double registerFee, Level level, Option option) {
        this.id = id;
        this.totalFee = totalFee;
        this.registerFee = registerFee;
        this.level = level;
        this.option = option;
    }

    public SchoolFee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public double getRegisterFee() {
        return registerFee;
    }

    public void setRegisterFee(double registerFee) {
        this.registerFee = registerFee;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "SchoolFee{" +
                "id=" + id +
                ", totalFee=" + totalFee +
                ", registerFee=" + registerFee +
                ", level=" + level +
                ", option=" + option +
                '}';
    }
}
