package com.sidof.api;

import com.sidof.model.Assessment;
import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.enumeration.AssessmentType;
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
@RequestMapping("/api/v1/sekou/assessment")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class AssessmentApi {
    private final AssessmentService assessmentService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody Assessment assessment) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("assessment", assessmentService.save(assessment)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("assessment created")
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getAssessments() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("assessments", assessmentService.getAssessmentList()))
                .status(OK)
                .statusCode(OK.value())
                .message("Assessment retrieved")
                .build());
    }

    @GetMapping("/{option}/{level}/{year}/{assessmentType}")
    public ResponseEntity<CustomResponse> getAssessmentsByOptionAndLevelAndt(
            @PathVariable("option") Option option, @PathVariable("level") Level level,
            @PathVariable("year") String year, @PathVariable("assessmentType") AssessmentType assessmentType) {
        Year year1 = Year.parse(year);
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("assessments", assessmentService.findByOptionAndLevelAndYearAndAssessmentType(option, level, year1, assessmentType)))
                .status(OK)
                .statusCode(OK.value())
                .message("assessments retrieved")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getAssessment(@PathVariable("id") Long id) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("assessment", assessmentService.getAssessment(id)))
                .status(OK)
                .statusCode(OK.value())
                .message("assessment retrieved")
                .build());
    }
}
