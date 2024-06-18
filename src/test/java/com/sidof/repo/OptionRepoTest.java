package com.sidof.repo;

import com.sidof.model.Option;
import com.sidof.model.Speciality;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Slf4j
class OptionRepoTest {
    @Autowired
    private OptionRepo underTest;
    @Autowired
    private SpecialityRepo specialityRepo;
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void canFindByName() {
//               given
        Speciality speciality = new Speciality(1L,"SOFTWARE ENGINEER");
        Speciality SOFTWARE = specialityRepo.save(speciality);
        Option GSI = new Option(1L,"GSI",SOFTWARE);
//        Option GSI = new Option(1L,"GSI",SOFTWARE);
        underTest.save(GSI);
        Optional<Option> expected = underTest.findByName(GSI.getName());
        assertEquals(Optional.of(GSI),expected);
        log.info("TEST REPO FIND BY NAME PASSED");

    }
}