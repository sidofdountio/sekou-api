package com.sidof.api;

import com.sidof.dto.TeacherDto;
import com.sidof.model.CourseOffering;
import com.sidof.service.TeacherService;
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
@RequestMapping("/api/v1/sekou/teacher")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class TeacherApi {
    private final TeacherService teacherService;


    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody TeacherDto dto) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                        .data(of("teacher",teacherService.save(dto)))
                .statusCode(CREATED.value())
                .message("Teacher created")
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getCoursesOfferings() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("teachers", teacherService.getTeachers()))
                .status(OK)
                .statusCode(OK.value())
                .message("Teachers retrieved")
                .build());
    }
}
