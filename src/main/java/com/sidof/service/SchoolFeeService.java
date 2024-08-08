package com.sidof.service;

import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.SchoolFee;
import com.sidof.repo.SchoolFeeRepo;
import com.sidof.service.inplementation.SchoolFeeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sidof.utils.FormatNumber.validNumber;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/08/2024  <br>
 * Version    : v1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolFeeService implements SchoolFeeServiceImpl {
    private final SchoolFeeRepo schoolFeeRepo;

    @Override
    public SchoolFee save(SchoolFee schoolFeeToSave) throws BadRequestException {
        if (!validNumber(schoolFeeToSave.getRegisterFee())) {
            log.error("Invalid Register Fee {}", schoolFeeToSave.getRegisterFee());
            throw new IllegalArgumentException("Invalid Register Fee");
        }
        if (!validNumber(schoolFeeToSave.getTotalFee())) {
            log.error("You Provide Not Valid Total School Fee {}", schoolFeeToSave.getTotalFee());
            throw new IllegalArgumentException("You Provide Not Valid Total School Fee");
        }
        log.info("saving new schoolFee {}", schoolFeeToSave);
        return schoolFeeRepo.save(schoolFeeToSave);
    }

    @Override
    public SchoolFee edit(SchoolFee schoolFeeToEdit) throws BadRequestException {
        boolean existsById = schoolFeeRepo.existsById(schoolFeeToEdit.getId());
        if (!existsById) {
            log.error("Id not found {}", schoolFeeToEdit.getId());
            throw new BadRequestException("Id not found");
        }
        log.info("Editing school fee {}", schoolFeeToEdit);
        return schoolFeeRepo.save(schoolFeeToEdit);
    }

    @Override
    public List<SchoolFee> getSchoolFees() {
        log.info("Fetching all school fee");
        return schoolFeeRepo.findAll();
    }

    @Override
    public SchoolFee getSchoolFee(Long schoolFeeId) throws BadRequestException {
        boolean existsById = schoolFeeRepo.existsById(schoolFeeId);
        if (!existsById) {
            log.error("school feee ID {}  not found", schoolFeeId);
            throw new BadRequestException("School fee is not found");
        }
        log.info("Fetching school fee by ID");
        return schoolFeeRepo.findById(schoolFeeId).get();
    }

    @Override
    public List<SchoolFee> findByOptionAndLevelAndYear(Option option, Level level) {
        log.info("Fetching all school fee by option {} and level {}", option, level);
        return schoolFeeRepo.findByOptionAndLevel(option, level);
    }
}
