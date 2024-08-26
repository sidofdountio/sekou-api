package com.sidof.api;

import com.sidof.model.School;
import com.sidof.service.SchoolService;
import com.sidof.utils.CustomResponse;
import lombok.AllArgsConstructor;
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
 * Since       : 13/07/2024  <br>
 * Version    : v1.0.0
 */

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/sekou/school")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class SchoolApi {
    private final SchoolService schoolService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody School school) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("school", schoolService.save(school)))
                .statusCode(CREATED.value())
                .message("School created")
                .build());
    }

    @PutMapping
    public ResponseEntity<CustomResponse> edite(@RequestBody School school) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("school", schoolService.edite(school)))
                .statusCode(CREATED.value())
                .message("School updated")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getSchool(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("school", schoolService.getSchool(id)))
                .status(OK)
                .statusCode(OK.value())
                .message("School retrieved")
                .build());
    }
}
