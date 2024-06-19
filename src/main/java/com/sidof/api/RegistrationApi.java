package com.sidof.api;

import com.sidof.dto.RegisterDto;
import com.sidof.service.RegisterService;
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
@RequestMapping("/api/v1/sekou/registration")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class RegistrationApi {
    private final RegisterService registerService;

    @GetMapping
    public ResponseEntity<CustomResponse> getRegistrations() {
        return new ResponseEntity<CustomResponse>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("register", registerService.getRegisters()))
                .message("Registrations retried")
                .status(OK)
                .statusCode(OK.value())
                .build(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getRegistration(@PathVariable("id") Long id) throws BadRequestException {
        return new ResponseEntity<CustomResponse>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("register", registerService.getRegister(id)))
                .message("Registration retried")
                .status(OK)
                .statusCode(OK.value())
                .build(), OK);
    }

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody RegisterDto registerDto) throws BadRequestException {
        return new ResponseEntity<CustomResponse>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("register", registerService.save(registerDto)))
                .message("Register created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build(), CREATED);
    }

}
