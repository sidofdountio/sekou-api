package com.sidof.service;

import com.sidof.model.Option;
import com.sidof.repo.OptionRepo;
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
public class OptionService {
    private final OptionRepo repo;

    public Option save(Option optionToSave) throws BadRequestException {
        Optional<Option> speciality = repo.findByName(optionToSave.getName());
        if (speciality.isPresent()) {
            log.error("A Option with this name {} already exist", optionToSave.getName());
            throw new BadRequestException("A option with this name" + optionToSave.getName() + " already exist.");
        }
        log.info("Saving new option {}", optionToSave);
        return repo.save(optionToSave);
    }

    public List<Option> getAllOption()  {
        log.info("Fetching option");
        return repo.findAll();
    }

    public Option getOption(Long id) throws BadRequestException {
        boolean existsById = repo.existsById(id);
        if (existsById) {
            log.error("A Option with this id {} already exist", id);
            throw new BadRequestException("A option with this id" + id + " already exist.");
        }
        log.info("Fetch  option {}", id);
        return repo.findById(id).get();
    }
}
