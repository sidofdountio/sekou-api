package com.sidof.service;

import com.sidof.model.*;
import com.sidof.model.enumeration.Gender;
import com.sidof.repo.SchoolFeeRepo;
import com.sidof.repo.StudentRepo;
import com.sidof.repo.StudentSchoolFeeRepo;
import org.apache.coyote.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.Optional;

import static java.time.LocalDate.of;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentSchoolFeeServiceTest {
    StudentSchoolFeeService underTest;
    @Mock
    StudentSchoolFeeRepo studentSchoolFeeRepo;
    @Mock
    StudentRepo studentRepo;
    @Mock
    SchoolFeeRepo schoolFeeRepo;

    @BeforeEach
    void setUp() {
        underTest = new StudentSchoolFeeService(studentRepo, schoolFeeRepo, studentSchoolFeeRepo);
    }

    @Test
    @DisplayName("Save new school fee")
    void save() throws BadRequestException {
//        Given
        var Level1 = new Level(1L, "Level1");
        var studentSchoolFee = getStudentSchoolFee(Level1);
//        Return a student object when they try to fetch a studentById
        when(studentRepo.findById(studentSchoolFee.getStudent().getId())).thenReturn(Optional.of(studentSchoolFee.getStudent()));
//        When
        underTest.save(studentSchoolFee);
        ArgumentCaptor<StudentSchoolFee> argumentCaptor = ArgumentCaptor.forClass(StudentSchoolFee.class);
//        Then
        verify(studentSchoolFeeRepo).save(argumentCaptor.capture());
        StudentSchoolFee argumentCaptorValue = argumentCaptor.getValue();
        Assertions.assertThat(argumentCaptorValue).isEqualTo(studentSchoolFee);
    }

    @Test
    @DisplayName("Throw error if the provide fee value is invalid")
    void TrowExceptionWhileSavingNewSchoolFeeIfProvideFeeIsInvalid() throws BadRequestException {
//        Given
        var Level1 = new Level(1L, "Level1");
        var studentSchoolFee = getStudentSchoolFee(Level1);

//        Return a student object when they try to fetch a studentById
        when(studentRepo.findById(studentSchoolFee.getStudent().getId())).thenReturn(Optional.of(studentSchoolFee.getStudent()));
        studentSchoolFee.setFirstPay(-150_000);

//        When
//        Then
        assertThatThrownBy(() ->
                underTest.save(studentSchoolFee))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid number for first pay");


    }

    @Test
    void TrowExceptionWhileSavingNewSchoolFeeWhenMultiplePayTypeHaveFistPayEqualToZero() throws BadRequestException {
//        Given
        var Level1 = new Level(1L, "Level1");
        var studentSchoolFee = getStudentSchoolFee(Level1);
//        Return a student object when they try to fetch a studentById
        when(studentRepo.findById(studentSchoolFee.getStudent().getId())).thenReturn(Optional.of(studentSchoolFee.getStudent()));
        studentSchoolFee.setFirstPay(0);
//        When
//        Then
        assertThatThrownBy(() ->
                underTest.save(studentSchoolFee))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("First pay cannot be equals to 0 since select multi pay");


    }

    @Test
    void TrowExceptionWhileSavingNewStudentSchoolFeeWhenSchoolFeeIsDeffrentToStudentTotalSchoolFee() throws BadRequestException {
//        Given
        var Level1 = new Level(1L, "Level1");
        var studentSchoolFee = getStudentSchoolFee(Level1);
//        Return a student object when they try to fetch a studentById
        when(studentRepo.findById(studentSchoolFee.getStudent().getId())).thenReturn(Optional.of(studentSchoolFee.getStudent()));
        studentSchoolFee.setPayMultiTime(false);
        studentSchoolFee.setPayOneTime(true);
        studentSchoolFee.setSchoolFeeTotal(0);
        when(schoolFeeRepo.findSchoolFeeByOptionAndLevel(studentSchoolFee.getOption(), Level1))
                .thenReturn(new SchoolFee(1L, 500_000, 50_000, Level1, studentSchoolFee.getOption()));
//        When
//        Then
        assertThatThrownBy(() ->
                underTest.save(studentSchoolFee))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("School fee not match with require");
    }

    private static StudentSchoolFee getStudentSchoolFee(Level Level1) {
        var SOFWAREEGINER = new Speciality(1L, "SOFWAREEGINER");
        var GSI = new Option(1L, "GSI", SOFWAREEGINER);

        var John = new Student(
                1L,
                "john",
                "doe",
                Gender.MALE,
                of(2000, 1, 1),
                "john@gmail.com");
        return new StudentSchoolFee(
                1L,
                false,
                true,
                150_000,
                0,
                0,
                500_000,
                Year.of(2024),
                John,
                Level1,
                GSI
        );
    }

    @Test
    void editStudentSchoolFee() throws BadRequestException {
        //        Given
        var Level1 = new Level(1L, "Level1");
        var studentSchoolFee = getStudentSchoolFee(Level1);
        given(studentSchoolFeeRepo.existsById(studentSchoolFee.getId())).willReturn(true);
//        when
//        Return a student object when they try to fetch a studentById
        when(studentRepo.findById(studentSchoolFee.getStudent().getId())).thenReturn(Optional.of(studentSchoolFee.getStudent()));
        when(schoolFeeRepo.findSchoolFeeByOptionAndLevel(studentSchoolFee.getOption(), Level1))
                .thenReturn(new SchoolFee(1L, 500_000, 50_000, Level1, studentSchoolFee.getOption()));
//        When
        studentSchoolFee.setPayMultiTime(true);
        studentSchoolFee.setPayOneTime(false);
        studentSchoolFee.setSecondPay(200_000);
        underTest.edit(studentSchoolFee);
    }

    @Test
    void throwExceptionWhileEditStudentSchoolFeeWhenFirstPayIsEqualToZero() throws BadRequestException {
        //        Given
        var Level1 = new Level(1L, "Level1");
        var studentSchoolFee = getStudentSchoolFee(Level1);
        given(studentSchoolFeeRepo.existsById(studentSchoolFee.getId())).willReturn(true);
//        when
//        Return a student object when they try to fetch a studentById
        when(studentRepo.findById(studentSchoolFee.getStudent().getId())).thenReturn(Optional.of(studentSchoolFee.getStudent()));
        when(schoolFeeRepo.findSchoolFeeByOptionAndLevel(studentSchoolFee.getOption(), Level1))
                .thenReturn(new SchoolFee(1L, 500_000, 50_000, Level1, studentSchoolFee.getOption()));
//        When
        studentSchoolFee.setPayMultiTime(true);
        studentSchoolFee.setPayOneTime(false);
        studentSchoolFee.setFirstPay(0);
        assertThatThrownBy(() ->
                underTest.edit(studentSchoolFee)
        )
                .hasMessageContaining("First pay for school fee cannot be 0")
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    void throwExceptionWhileEditStudentSchoolFeeWhenSecondPayIsEqualToZero() throws BadRequestException {
        //        Given
        var Level1 = new Level(1L, "Level1");
        var studentSchoolFee = getStudentSchoolFee(Level1);
        given(studentSchoolFeeRepo.existsById(studentSchoolFee.getId())).willReturn(true);
//        when
//        Return a student object when they try to fetch a studentById
        when(studentRepo.findById(studentSchoolFee.getStudent().getId())).thenReturn(Optional.of(studentSchoolFee.getStudent()));
        when(schoolFeeRepo.findSchoolFeeByOptionAndLevel(studentSchoolFee.getOption(), Level1))
                .thenReturn(new SchoolFee(1L, 500_000, 50_000, Level1, studentSchoolFee.getOption()));
//        When
        studentSchoolFee.setPayMultiTime(true);
        studentSchoolFee.setPayOneTime(false);
        studentSchoolFee.setSecondPay(0);
        assertThatThrownBy(() -> underTest.edit(studentSchoolFee))
                .hasMessageContaining("Second pay for school fee cannot be 0 since first pay has value.")
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    void findByOptionAndLevelAndYear() {
    }
}