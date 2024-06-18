package com.sidof.service.inplementation;

import com.sidof.model.Option;
import org.apache.coyote.BadRequestException;

import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 18/06/2024  <br>
 * Version    : v1.0.0
 */

public interface OptionServiceImpl {
    List<Option> getOptions();
    Option save(Option option ) throws BadRequestException;
}
