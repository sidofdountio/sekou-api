package com.sidof.api;

import com.sidof.dto.CourseOfferingDto;
import com.sidof.model.CourseOffering;
import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.service.CourseOfferingService;
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
@RequestMapping("/api/v1/sekou/course-offering")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class CourseOfferingApi {
    private final CourseOfferingService courseOfferingService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody CourseOffering courseOfferingToSave) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("courseOffering", courseOfferingService.save(courseOfferingToSave)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("course offering created")
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getCoursesOfferings() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("courseOfferings", courseOfferingService.getCourseOfferings()))
                .status(OK)
                .statusCode(OK.value())
                .message("course offering retrieved")
                .build());
    }

    @GetMapping("/{option}/{level}/{year}")
    public ResponseEntity<CustomResponse> getCoursesOfferingsByOptionAndLevelAndt(
            @PathVariable("option") Option option, @PathVariable("level") Level level, @PathVariable("year") String year) {
        Year year1 = Year.parse(year);
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("courseOfferings", courseOfferingService.findByOptionAndLevelAndYear(option, level, year1)))
                .status(OK)
                .statusCode(OK.value())
                .message("course offering retrieved")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getCourseOffering(@PathVariable("id") Long id) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("courseOffering", courseOfferingService.getCourseOffering(id)))
                .status(OK)
                .statusCode(OK.value())
                .message("course offering retrieved")
                .build());
    }
}
