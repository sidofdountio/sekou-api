package com.sidof.service.inplementation;

import com.sidof.dto.RegisterDto;
import com.sidof.model.Register;
import org.apache.coyote.BadRequestException;

import java.util.List;

/**
 * Author       : sidof  <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a>  <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
public interface RegisterImpl {
    List<Register> getRegisters();
    Register save(RegisterDto registerDtoToSave) throws BadRequestException;
    Register update(RegisterDto registerDtoToSave) throws BadRequestException;
    Register getRegister(Long id) throws BadRequestException;
}
