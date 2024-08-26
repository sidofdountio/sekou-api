package com.sidof.model;

import com.sidof.model.enumeration.SchoolPayStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.Year;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/08/2024  <br>
 * Version    : v1.0.0
 */

@Entity
public class StudentSchoolFee {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "school_fee_id_sequence")
    @SequenceGenerator(name = "school_fee_id_sequence", allocationSize = 1, sequenceName = "school_fee_id_sequence")
    private Long id;
    private boolean payOneTime;
    private boolean payMultiTime;
    @Column(nullable = true)
    private double firstPay;
    @Column(nullable = true)
    private double secondPay;
    @Column(nullable = true)
    private double thirdPay;
    @Column(nullable = false)
    private double schoolFeeTotal;
    @Column(nullable = false, name = "years")
    private Year year;
    private Year endYear;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_school_fee_student"))
    private Student student;
    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_school_fees_level"))
    private Level level;
    @ManyToOne
    @JoinColumn(name = "option_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_school_fee_option"))
    private Option option;
    @Column(nullable = true)
    private LocalDateTime latestDate;
    @Enumerated(STRING)
    @Column(
            columnDefinition = "VARCHAR(255)  default 'NOPAY'")
    private SchoolPayStatus schoolPayStatus;

    public StudentSchoolFee(Long id, boolean payOneTime, boolean payMultiTime, double firstPay, double secondPay, double thirdPay, double schoolFeeTotal, Year endYear, Student student, Level level, Option option) {
        this.id = id;
        this.payOneTime = payOneTime;
        this.payMultiTime = payMultiTime;
        this.firstPay = firstPay;
        this.secondPay = secondPay;
        this.thirdPay = thirdPay;
        this.schoolFeeTotal = schoolFeeTotal;
        this.endYear = endYear;
        this.student = student;
        this.level = level;
        this.option = option;
    }

    public StudentSchoolFee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPayOneTime() {
        return payOneTime;
    }

    public void setPayOneTime(boolean payOneTime) {
        this.payOneTime = payOneTime;
    }

    public boolean isPayMultiTime() {
        return payMultiTime;
    }

    public void setPayMultiTime(boolean payMultiTime) {
        this.payMultiTime = payMultiTime;
    }

    public double getFirstPay() {
        return firstPay;
    }

    public void setFirstPay(double firstPay) {
        this.firstPay = firstPay;
    }

    public double getSecondPay() {
        return secondPay;
    }

    public void setSecondPay(double secondPay) {
        this.secondPay = secondPay;
    }

    public double getThirdPay() {
        return thirdPay;
    }

    public void setThirdPay(double thirdPay) {
        this.thirdPay = thirdPay;
    }

    public double getSchoolFeeTotal() {
        return schoolFeeTotal;
    }

    public void setSchoolFeeTotal(double schoolFeeTotal) {
        this.schoolFeeTotal = schoolFeeTotal;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Year getEndYear() {
        return endYear;
    }

    public void setEndYear(Year endYear) {
        this.endYear = endYear;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    public LocalDateTime getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(LocalDateTime latestDate) {
        this.latestDate = latestDate;
    }

    @Override
    public String toString() {
        return "StudentSchoolFee{" +
                "id=" + id +
                ", payOneTime=" + payOneTime +
                ", payMultiTime=" + payMultiTime +
                ", firstPay=" + firstPay +
                ", secondPay=" + secondPay +
                ", thirdPay=" + thirdPay +
                ", schoolFeeTotal=" + schoolFeeTotal +
                ", year=" + year +
                ", endYear=" + endYear +
                ", student=" + student +
                ", level=" + level +
                ", option=" + option +
                ", latestDate=" + latestDate +
                '}';
    }
}
