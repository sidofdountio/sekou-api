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

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
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
        Speciality SOFTWARE = specialityRepo.save(new Speciality(1L,"SOFTWARE ENGINEER"));
        Option GSI = underTest.save(new Option(1L,"GSI",SOFTWARE));
        Optional<Option> expected = underTest.findByName(GSI.getName());
        assertNotNull(expected);

    }
}