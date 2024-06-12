package com.sidof.service;

import com.sidof.model.Option;
import com.sidof.model.Speciality;
import com.sidof.repo.OptionRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class OptionServiceTest {
    private OptionService underTest;
    @Mock private OptionRepo optionRepo;
    @BeforeEach
    void setUp() {
        underTest = new OptionService(optionRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void canSaveNewOption() throws BadRequestException {
        //                given
        Speciality  SOFTWARE = new Speciality(1L,"SOFTWARE ENGINEER");
        Option GSI = new Option(1L,"GSI",SOFTWARE,new ArrayList<>(),new ArrayList<>());
        underTest.save(GSI);
//        when
        ArgumentCaptor<Option>argumentCaptor = ArgumentCaptor.forClass(Option.class);
        verify(optionRepo).save(argumentCaptor.capture());
        Option argumentCaptorValue = argumentCaptor.getValue();
//        then
        assertThat(GSI).isEqualTo(argumentCaptorValue);
        log.warn("TEST Save new Option passed");
    }

    @Test
    void shouldThrowErrorWhenOptionNameExist() throws BadRequestException {
//       given
        Speciality  SOFTWARE = new Speciality(1L,"SOFTWARE ENGINEER");
        Option GSI = new Option(1L,"GSI",SOFTWARE,new ArrayList<>(),new ArrayList<>());
//        when
        given(optionRepo.findByName(GSI.getName())).willReturn(Optional.of(GSI));
        assertThatThrownBy(()->underTest.save(GSI))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("A option with this name" + GSI.getName() + " already exist.");
//        then
        verify(optionRepo, never()).save(any());

    }

    @Test
    void getOneOption() {
    }

    @Test
    void CanGetAllOption() throws BadRequestException {
//                given
        Speciality  SOFTWARE = new Speciality(1L,"SOFTWARE ENGINEER");
        Speciality  FINANCE = new Speciality(2L,"COMPTABILITE ET GESTION");
        Option GSI = new Option(1L,"GSI",SOFTWARE,new ArrayList<>(),new ArrayList<>());
        Option CGE = new Option(2L,"CGE",FINANCE,new ArrayList<>(),new ArrayList<>());
        List<Option> list = List.of(GSI,CGE);
        underTest.save(GSI);
        underTest.save(CGE);
//        when
        //        Force to save and return a list
        when(underTest.getAllOption())
                .thenReturn(list);
        List<Option> expected = underTest.getAllOption();
//        then
        verify(optionRepo).findAll();
        assertThat(expected.size()).isEqualTo(2);
        log.info("TEST GET ALL OPTIONS PASSED");
    }
}