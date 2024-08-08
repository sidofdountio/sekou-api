package com.sidof.api;

import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.SchoolFee;
import com.sidof.model.StudentSchoolFee;
import com.sidof.service.SchoolFeeService;
import com.sidof.service.StudentSchoolFeeService;
import com.sidof.utils.CustomResponse;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;

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
@RequestMapping("/api/v1/sekou/student-school-fee")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class StudentSchoolFeeApi {
    private final StudentSchoolFeeService studentSchoolFeeService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody StudentSchoolFee studentSchoolFeeToSave) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("studentSchoolFee", studentSchoolFeeService.save(studentSchoolFeeToSave)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("student school fee created")
                .build());
    }

    @PutMapping
    public ResponseEntity<CustomResponse> update(@RequestBody StudentSchoolFee studentSchoolFeeToUpdate) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("student studentSchoolFee", studentSchoolFeeService.edit(studentSchoolFeeToUpdate)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("student school fee updated")
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getStudentSchoolFees() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("studentSchoolFees", studentSchoolFeeService.getStudentSchoolFees()))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("student school fee retrieved")
                .build());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CustomResponse> getStudentSchoolFee(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("studentSchoolFee", studentSchoolFeeService.getStudentSchoolFee(id)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("student school fee retrieved")
                .build());
    }

    @GetMapping(path = "{option}/{level}/{year}")
    public ResponseEntity<CustomResponse> getStudentSchoolFeeByOptionAndLevelAndYear(@PathVariable Option option, @PathVariable Level level, @PathVariable Year year)  {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("studentSchoolFees", studentSchoolFeeService.findByOptionAndLevelAndYear(option,level,year)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Student school fee retrieved By option, Level and Year")
                .build());
    }
}
