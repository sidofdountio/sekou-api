package com.sidof.service;

import com.sidof.dto.RegisterDto;
import com.sidof.model.Register;
import com.sidof.model.Student;
import com.sidof.repo.RegisterRepo;
import com.sidof.repo.StudentRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;

import static com.sidof.model.enumeration.Gender.MALE;
import static java.time.LocalDate.of;
import static java.time.Year.*;
import static java.time.Year.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {
    private RegisterService underTest;
   @Mock
   private RegisterRepo repo;
    @Mock
    private StudentRepo studentRepo;

    @BeforeEach
    void setUp() {
        underTest = new RegisterService(repo,studentRepo);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void canGetAllRegisters() {
        underTest.getRegisters();
        verify(repo).findAll();
    }

    @Test
    void canSaveRegisterNewStudent() throws BadRequestException {
        //        given
        var student = Student.builder()
                .id(1L)
                .firstName("James")
                .lastName("Golsing")
                .email("jamesgolsing@gmail.com")
                .gender(MALE)
                .dateOfBirth(of(1985, 10, 12))
                .build();
        RegisterDto registerDto = new RegisterDto();
        registerDto.setId(1L);
        registerDto.setStudent(student);
        registerDto.setFeeRegister(55000);
        registerDto.setStartDate(now());
        registerDto.setEndDate(Year.of(2025));

        Register register = Register.builder()
                .id(1L)
                .student(student)
                .feeRegister(55000)
                .endDate(Year.of(2025))
                .startDate(now())
                .build();
        underTest.save(registerDto);
//        when
        ArgumentCaptor<Register>argumentCaptor=ArgumentCaptor.forClass(Register.class);
        verify(repo).save(argumentCaptor.capture());
        Register argumentCaptorValue = argumentCaptor.getValue();
//        then

    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    @Disabled
    void getRegister() {
    }
}