package com.sidof.api;

import com.sidof.model.Level;
import com.sidof.model.Speciality;
import com.sidof.service.SpecialityService;
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
@RequestMapping("/ap/v1/sekou/speciality")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class SpecialityApi {

    private final SpecialityService specialityService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody Speciality speciality) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("speciality", specialityService.save(speciality)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Speciality created")
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getSpeciality() throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("specialities", specialityService.getSpeciality()))
                .status(OK)
                .statusCode(OK.value())
                .message("Specialities retrieved")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getSpeciality(@PathVariable("id")Long id) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("speciality", specialityService.getSpeciality(id)))
                .status(OK)
                .statusCode(OK.value())
                .message("Speciality retrieved")
                .build());
    }
}
