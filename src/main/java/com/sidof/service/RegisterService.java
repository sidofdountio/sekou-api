package com.sidof.service;

import com.sidof.model.Register;
import com.sidof.model.Student;
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

import static com.sidof.utils.FormatNumber.validNumber;
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
    public Register save(Register registerDtoToSave) throws BadRequestException {
        var existStudent = studentRepo.findById(registerDtoToSave.getStudent().getId());
        Student student = existStudent.get();
        student.setRegisted(true);
        if (!existStudent.isPresent()) {
            log.info("Student ID {} does exist", registerDtoToSave.getId());
            throw new BadRequestException("Student id does exist");
        }
        if (!validNumber((registerDtoToSave.getFeeRegister()))) {
            log.info("Not valid registration fee {}", registerDtoToSave.getFeeRegister());
            throw new BadRequestException("A registration fee can't be " + registerDtoToSave.getFeeRegister());
        }

        var register = Register.builder()
                .id(null)
                .student(registerDtoToSave.getStudent())
                .feeRegister(registerDtoToSave.getFeeRegister())
                .registerDate(now())
                .startDate(registerDtoToSave.getEndDate().minusYears(1))
                .endDate(registerDtoToSave.getEndDate())
                .level(registerDtoToSave.getLevel())
                .option(registerDtoToSave.getOption())
                .build();
        /**
         * Update register student  to true.
         * This is only for a year.
         */
        studentRepo.save(student);
        log.info("Register new student {}", register);
        return repo.save(register);
    }

    @Override
    public Register update(Register registerToUpdate) throws BadRequestException {
        boolean existStudent = studentRepo.existsById(registerToUpdate.getStudent().getId());
        if (!existStudent) {
            log.info("Student id {} does exist", registerToUpdate.getId());
            throw new BadRequestException("Student id does exist");
        }
        Optional<Register> optionalRegister = repo.findById(registerToUpdate.getId());
        if (optionalRegister.isEmpty()) {
            log.error("register id {} does exist", registerToUpdate.getId());
            throw new BadRequestException("register id {} does exist");
        }
        if (!validNumber((registerToUpdate.getFeeRegister()))) {
            log.info("A registration fee cannot {}", registerToUpdate.getFeeRegister());
            throw new BadRequestException("A registration fee can't be " + registerToUpdate.getFeeRegister());
        }
        registerToUpdate.setStartDate(registerToUpdate.getEndDate().minusYears(1));
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
