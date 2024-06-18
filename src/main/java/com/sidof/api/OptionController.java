package com.sidof.api;

import com.sidof.model.Option;
import com.sidof.model.Speciality;
import com.sidof.service.OptionService;
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
@RequestMapping("/ap/v1/sekou/option")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class OptionController {
    private final OptionService optionService;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@RequestBody Option option) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("option", optionService.save(option)))
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Option created")
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getOptions()  {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("options", optionService.getOptions()))
                .status(OK)
                .statusCode(OK.value())
                .message("options retrieved")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getOption(@PathVariable("id")Long id) throws BadRequestException {
        return ResponseEntity.ok(CustomResponse.builder()
                .timeStamp(now())
                .data(of("option", optionService.getOption(id)))
                .status(OK)
                .statusCode(OK.value())
                .message("option retrieved")
                .build());
    }
}
