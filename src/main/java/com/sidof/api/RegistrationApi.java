package com.sidof.api;

import com.sidof.dto.RegisterDto;
import com.sidof.dto.StudentRequest;
import com.sidof.service.RegisterService;
import com.sidof.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 10/06/2024  <br>
 * Version    : v1.0.0
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/ap/v1/sekou/registration")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class RegistrationApi {
    private final RegisterService registerService;

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
