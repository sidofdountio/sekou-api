package com.sidof.service;

import com.sidof.model.Speciality;
import com.sidof.repo.SpecialityRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class SpecialityServiceTest {
    @Autowired
    private SpecialityService underTest;
    @Mock
    private SpecialityRepo specialityRepo;


    @BeforeEach
    void setUp() {
        underTest = new SpecialityService(specialityRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() throws BadRequestException {
//                given
        Speciality speciality = new Speciality(1L, "SOFTWARE ENGINEER");
        underTest.save(speciality);
//        when
        ArgumentCaptor<Speciality> argumentCaptor = ArgumentCaptor.forClass(Speciality.class);
        verify(specialityRepo).save(argumentCaptor.capture());
        Speciality argumentCaptorValue = argumentCaptor.getValue();
//        then
        assertEquals(speciality, argumentCaptorValue);
        log.info("TEST ADD NEW  SPECIALITY PASSED");
    }

    @Test
    void showErrorWhileAddExistSpeciality() throws BadRequestException {
//                given
        Speciality speciality = new Speciality(1L, "SOFTWARE ENGINEER");
//        when
        given(specialityRepo.findByName(speciality.getName()))
                .willReturn(Optional.of(speciality));
        assertThatThrownBy(() -> underTest.save(speciality))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("A speciality with this name" + speciality.getName() + "exist.");
//        then
        verify(specialityRepo, never()).save(any());
        log.info("TEST showErrorWhileAddExistSpeciality PASSED");
    }

    @Test
    void getSpeciality() {
    }

    @Test
    void canGetAllSpeciality() throws BadRequestException {
//        given
        Speciality speciality = new Speciality(1L, "SOFTWARE ENGINEER");
        Speciality speciality1 = new Speciality(2L, "COMPTABILITE ET GESTION");
        List<Speciality> list = List.of(speciality, speciality1);
        underTest.save(speciality1);
        underTest.save(speciality);
//        when
        //        Force to save and return a list
        when(underTest.getSpeciality())
                .thenReturn(list);
        List<Speciality> expected = underTest.getSpeciality();
//        then
        verify(specialityRepo).findAll();
        assertThat(expected.size()).isEqualTo(2);
        log.info("TEST GET ALL SPECIALITY PASSED");
    }
}