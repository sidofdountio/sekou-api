package com.sidof.api;

import com.sidof.model.Assessment;
import com.sidof.model.AssessmentPeriod;
import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.enumeration.AssessmentType;
import com.sidof.service.AssessmentPeriodService;
import com.sidof.service.AssessmentService;
import com.sidof.utils.CustomResponse;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 09/07/2024  <br>
 * Version    : v1.0.0
 */     
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/sekou/period")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class AssessmentPeriodApi {
    private final AssessmentPeriodService assessmentPeriodService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody AssessmentPeriod period) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("period", assessmentPeriodService.save(period)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("period created")
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getPeriods() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("periods", assessmentPeriodService.getPeriodAssessments()))
                .status(OK)
                .statusCode(OK.value())
                .message("period retrieved")
                .build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getPeriod(@PathVariable("id") Long id) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("period", assessmentPeriodService.getPeriod(id)))
                .status(OK)
                .statusCode(OK.value())
                .message("Period retrieved")
                .build());
    }
}
