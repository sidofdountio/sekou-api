package com.sidof.api;

import com.sidof.model.Course;
import com.sidof.model.CourseEnrollment;
import com.sidof.service.CourseEnrollmentService;
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
@RequestMapping("/ap/v1/sekou/courseEnrollment")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class CourseEnrollmentApi {
    private  final CourseEnrollmentService courseEnrollmentService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody CourseEnrollment courseEnrollment) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("courseEnrollment", courseEnrollmentService.save(courseEnrollment)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("courseEnrollment created")
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getCoursesEnrollments(){
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("coursesEnrollment", courseEnrollmentService.getEnrollments()))
                .status(OK)
                .statusCode(OK.value())
                .message("courseEnrollment retrieved")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getCourseEnrollment(@PathVariable("id")Long id) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("courseEnrollment",courseEnrollmentService.getCourseEnrollment(id)))
                .status(OK)
                .statusCode(OK.value())
                .message("courseEnrollment retrieved")
                .build());
    }
}
