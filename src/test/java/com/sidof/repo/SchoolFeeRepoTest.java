package com.sidof.repo;

import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.SchoolFee;
import com.sidof.model.Speciality;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class SchoolFeeRepoTest {
    @Autowired
    private SchoolFeeRepo underTest;
    @Autowired
    private LevelRepo levelRepo;
    @Autowired
    private OptionRepo optionRepo;
    @Autowired
    private SpecialityRepo specialityRepo;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findSchoolFeeByOptionAndLevel() {
        var Level1 = new Level(1L, "Level1");
        levelRepo.save(Level1);
        var SOFWAREEGINER = new Speciality(1L, "SOFWAREEGINER");
        specialityRepo.save(SOFWAREEGINER);
        var GSI = new Option(1L, "GSI", SOFWAREEGINER);
        optionRepo.save(GSI);
        SchoolFee schoolFee = new SchoolFee(1L, 500_000, 50_000, Level1, GSI);
        SchoolFee save = underTest.save(schoolFee);
        SchoolFee schoolFeeByOptionAndLevel = underTest.findSchoolFeeByOptionAndLevel(GSI, Level1);
        System.out.println("+-----------------OUTPUT------------------+");
        System.out.println(schoolFeeByOptionAndLevel);
        assertThat(schoolFeeByOptionAndLevel).isEqualTo(save);
    }
}