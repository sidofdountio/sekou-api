package com.sidof.service;

import com.sidof.dto.RegisterDto;
import com.sidof.model.Register;
import com.sidof.repo.RegisterRepo;
import com.sidof.repo.StudentRepo;
import com.sidof.service.inplementation.RegisterImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.time.LocalDate.now;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RegisterService implements RegisterImpl {
    private final RegisterRepo repo;
    private final StudentRepo studentRepo;

    @Override
    public List<Register> getRegisters() {
        log.info("Fetching students");
        return repo.findAll();
    }

    /**
     * Get the @param endDate from user and make minus 1 to get the start year value
     *
     * @param registerDtoToSave
     * @return Register
     * @throws BadRequestException
     */
    @Override
    public Register save(RegisterDto registerDtoToSave) throws BadRequestException {
        boolean existStudent = studentRepo.existsById(registerDtoToSave.getStudent().getId());
        if (!existStudent) {
            log.info("Student id {} does exist", registerDtoToSave.getId());
            throw new BadRequestException(format("Student id {} does exist", registerDtoToSave.getId()));
        }
        if (registerDtoToSave.getFeeRegister() == 0) {
            log.info("A registration fee can't be {}", registerDtoToSave.getFeeRegister());
            throw new BadRequestException("A registration fee can't be " + registerDtoToSave.getFeeRegister());
        }
        var register = Register.builder()
                .student(registerDtoToSave.getStudent())
                .feeRegister(registerDtoToSave.getFeeRegister())
                .registerDate(now())
                .startDate(registerDtoToSave.getEndDate().minusYears(1))
                .endDate(registerDtoToSave.getEndDate())
                .build();
        log.info("Register new student {}", register);
        return repo.save(register);
    }

    @Override
    public Register update(RegisterDto registerDtoToSave) throws BadRequestException {
        Optional<Register> optionalRegister = repo.findById(registerDtoToSave.getId());
        if (optionalRegister.isEmpty()) {
            log.error("register id {} does exist", registerDtoToSave.getId());
            throw new BadRequestException(format("register id {} does exist", registerDtoToSave.getId()));
        }
        var registerToUpdate = Register.builder()
                .student(registerDtoToSave.getStudent())
                .feeRegister(registerDtoToSave.getFeeRegister())
                .registerDate(now())
                .startDate(registerDtoToSave.getStartDate())
                .endDate(registerDtoToSave.getEndDate())
                .build();
        log.info("Registration updated");
        return repo.save(registerToUpdate);
    }

    @Override
    public Register getRegister(Long id) throws BadRequestException {
        log.info("Fetch register id {}", id);
        boolean existsById = repo.existsById(id);
        if (!existsById) {
            throw new BadRequestException(format("Registration id {} does exist", id));
        }
        return repo.findById(id).get();
    }
}
