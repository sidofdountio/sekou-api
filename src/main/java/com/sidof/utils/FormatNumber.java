package com.sidof.utils;

import com.sidof.model.Option;
import com.sidof.model.Speciality;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 08/06/2024  <br>
 * Version    : v1.0.0
 */
@Slf4j
public class FormatNumber {

    public void formatDouble(double numberToFormat){
        DecimalFormatSymbols  decimalFormatSymbols = new DecimalFormatSymbols();
        String pattern = "###.##";
        DecimalFormat decimalFormat= new DecimalFormat();
        StringBuffer stringBuffer = null;
        String formatted = decimalFormat.format(numberToFormat);
    }

    public static boolean validNumber(int number){
        if (number < 0){
            log.error("Cannot accept negative number {}",number);
            throw new IllegalArgumentException("Cannot accept negative number "+number);
        }
        return true;
    }

    public static boolean validNumber(double number){
        if (number < 0){
            log.error("Cannot accept negative number {}",number);
            throw new IllegalArgumentException("Cannot accept negative number "+number);
        }
        return true;
    }
}
