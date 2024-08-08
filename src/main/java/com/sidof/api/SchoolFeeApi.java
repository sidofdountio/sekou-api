package com.sidof.api;

import com.sidof.model.SchoolFee;
import com.sidof.service.SchoolFeeService;
import com.sidof.utils.CustomResponse;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 08/08/2024  <br>
 * Version    : v1.0.0
 */

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/sekou/school-fee")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class SchoolFeeApi {
    private final SchoolFeeService schoolFeeService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody SchoolFee schoolFeeToSave) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("schoolFee", schoolFeeService.save(schoolFeeToSave)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("school fee created")
                .build());
    }

    @PutMapping
    public ResponseEntity<CustomResponse> update(@RequestBody SchoolFee schoolFeeToUpdate) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("schoolFee", schoolFeeService.edit(schoolFeeToUpdate)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("school fee updated")
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getSchoolFees() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("schoolFees", schoolFeeService.getSchoolFees()))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("school fee retrieved")
                .build());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CustomResponse> getSchoolFee(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("schoolFee", schoolFeeService.getSchoolFees()))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("school fee retrieved")
                .build());
    }
}
