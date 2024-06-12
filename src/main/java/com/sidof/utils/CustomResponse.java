package com.sidof.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 10/06/2024  <br>
 * Version    : v1.0.0
 */

/**
 *  CustomResponse Class it provide to customize the output of all htt RESPONSE.<br>
 *  With   @Map<?, ?> data properties tha can contain any object
 *
 *    @LocalDateTime timeStamp
 *    @int statusCode
 *    @HttpStatus status
 *    @String reason
 *    @String message;
 *    @String developerMessage
 *    @Map<?, ?> data
 */
@Data
@SuperBuilder
@JsonInclude(NON_NULL)
public class CustomResponse  {
    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected Map<?, ?> data;
}