package com.sidof.api;

import com.sidof.model.Level;
import com.sidof.service.LevelService;
import com.sidof.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 10/06/2024  <br>
 * Version    : v1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sekou/level")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class LevelApi {
    private final LevelService levelService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody Level levelToSave) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("level", levelService.save(levelToSave)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Level created")
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getLevels() throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("levels", levelService.getLevels()))
                .status(OK)
                .statusCode(OK.value())
                .message("Levels retrieved")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getLevel(@PathVariable("id") Long id) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("level", levelService.getLevel(id)))
                .status(OK)
                .statusCode(OK.value())
                .message("Level retrieved")
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> delete(@PathVariable("id") Long id) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("level", levelService.delete(id)))
                .status(OK)
                .statusCode(OK.value())
                .message("Level deleted")
                .build());
    }
}
