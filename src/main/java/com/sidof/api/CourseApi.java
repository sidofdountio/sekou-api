package com.sidof.api;

import com.sidof.model.Course;
import com.sidof.service.CourseService;
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
@RequestMapping("/api/v1/sekou/course")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class CourseApi {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody Course course) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("course", courseService.save(course)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("course created")
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getCourses() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("courses", courseService.getCourses()))
                .status(OK)
                .statusCode(OK.value())
                .message("courses retrieved")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getCourse(@PathVariable("id") Long id) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("course", courseService.getCourse(id)))
                .status(OK)
                .statusCode(OK.value())
                .message("course retrieved")
                .build());
    }
}
