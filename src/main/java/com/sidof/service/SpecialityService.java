package com.sidof.service;

import com.sidof.model.Speciality;
import com.sidof.repo.SpecialityRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SpecialityService {
    private final SpecialityRepo specialityRepo;

    public Speciality save(Speciality specialityToSave) throws BadRequestException {
        Optional<Speciality> speciality = specialityRepo.findByName(specialityToSave.getName());
        if (speciality.isPresent()) {
            log.error("A speciality with this name {} already exist", specialityToSave.getName());
            throw new BadRequestException("A speciality with this name" + specialityToSave.getName() + "exist.");
        }
        log.info("Saving new speciality {}", specialityToSave);
        return specialityRepo.save(specialityToSave);
    }

    public Speciality getSpeciality(Long id) throws BadRequestException {
        log.info("Fetch speciality id :{}", id);
        return specialityRepo.findById(id).get();
    }

    public List<Speciality> getSpeciality () throws BadRequestException {
        log.info("Fetching  speciality List");
        return specialityRepo.findAll();
    }
}
