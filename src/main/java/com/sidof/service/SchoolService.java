package com.sidof.service;

import com.sidof.model.School;
import com.sidof.repo.SchoolRepo;
import com.sidof.service.inplementation.SchoolServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.Year;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 23/08/2024  <br>
 * Version    : v1.0.0
 */
@Service
@Slf4j
@AllArgsConstructor
public class SchoolService implements SchoolServiceImpl {
    private final SchoolRepo repo;

    @Override
    public School save(School schoolToSave) {
        boolean active = true;
        inActivatePreviousSchool(active);
        Year provideSchoolYear = schoolToSave.getSchoolYear().minusYears(1);
        schoolToSave.setYears(provideSchoolYear);
        schoolToSave.setShortName(schoolToSave.getShortName().toUpperCase());
        log.info("Saving new year school");
        return repo.save(schoolToSave);
    }

    @Override
    public School edite(School schoolToSave) throws BadRequestException {
        if (!repo.existsById(schoolToSave.getId())) {
            log.error("ID not found");
            throw new BadRequestException("ID not found");
        }
        boolean active = true;
        inActivatePreviousSchool(active);
        Year schoolYear = schoolToSave.getSchoolYear();
        Year provideSchoolYear = schoolToSave.getYears().minusYears(1);
        schoolToSave.setYears(provideSchoolYear);
        log.info("Updating new year school");
        return repo.save(schoolToSave);
    }

    private void inActivatePreviousSchool(boolean active) {
        School activeSchool = repo.findByActive(active);
        if (activeSchool != null) {
            activeSchool.setActive(false);
            log.info("Previous active school inactive");
            repo.save(activeSchool);
        }

    }

    @Override
    public School getSchool(Long id) {
        log.error("Fetch by ID");
        return repo.findById(id).get();
    }

    @Override
    public School getSchoolByActive(boolean active) {
        log.info("Fetching by active");
        return repo.findByActive(active);
    }
}
