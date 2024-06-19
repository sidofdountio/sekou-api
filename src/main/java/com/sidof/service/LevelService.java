package com.sidof.service;

import com.sidof.model.Level;
import com.sidof.repo.LevelRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LevelService {
    private final LevelRepo levelRepo;

    public Level save(Level levelToSave) throws BadRequestException {
        Optional<Level> optionalLevel = levelRepo.findByName(levelToSave.getName());
        if(optionalLevel.isPresent()){
            log.error("A level with this name {} already exist",levelToSave.getName());
            throw new BadRequestException("A level with this name" +levelToSave.getName() +" already exist.");
        }
        log.info("Saving new level {}",levelToSave);
        return  levelRepo.save(levelToSave);
    }

    public Level getLevel(Long id){
        log.info("Fetching Level {}",id);
        return levelRepo.findById(id).get();
    }

    public List<Level> getLevels(){
        log.info("Fetching Levels");
        return levelRepo.findAll();
    }

    public Boolean delete(Long id){
        log.info("Deleted Level {}",id);
        levelRepo.deleteById(id);
        return TRUE;
    }
}
