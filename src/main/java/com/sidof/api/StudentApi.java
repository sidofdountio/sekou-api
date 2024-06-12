package com.sidof.api;

import com.sidof.dto.StudentDto;
import com.sidof.dto.StudentRequest;
import com.sidof.service.StudentService;
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
@RequestMapping("/ap/v1/sekou/student")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class StudentApi {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody StudentRequest studentRequest) throws BadRequestException {
        return new ResponseEntity<CustomResponse>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("student", studentService.save(studentRequest)))
                .message("Student created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build(), CREATED);
    }

    @PutMapping
    public ResponseEntity<CustomResponse> update(@RequestBody StudentDto studentDto) throws BadRequestException {
        return new ResponseEntity<CustomResponse>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("student", studentService.update(studentDto)))
                .message("Student updated")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build(), CREATED);
    }

    @GetMapping
    public ResponseEntity<CustomResponse> students() {
        return new ResponseEntity<CustomResponse>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("students", studentService.getStudents()))
                .message("Students retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> student(@PathVariable("id") Long id) throws BadRequestException {
        return new ResponseEntity<CustomResponse>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("students", studentService.getStudent(id)))
                .message("Student retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build(), CREATED);
    }


}
