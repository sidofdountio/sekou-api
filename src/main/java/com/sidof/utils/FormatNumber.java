package com.sidof.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 08/06/2024  <br>
 * Version    : v1.0.0
 */
@Slf4j
public class FormatNumber {
    String regexPositiveAndNegative = "^\\d+(\\.\\d+)?$";
    static String regex = "^\\d+(\\.\\d+)?$";


    public void formatDouble(double numberToFormat) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        String pattern = "###.##";
        DecimalFormat decimalFormat = new DecimalFormat();
        StringBuffer stringBuffer = null;
        String formatted = decimalFormat.format(numberToFormat);
    }

    public static boolean validNumber(int number) {
        boolean valid = true;
        Pattern pattern = Pattern.compile(regex);
        String stringNumber = String.valueOf(number);
        Matcher matcher = pattern.matcher(stringNumber);
        if (number < 0) {
            log.error("Cannot accept negative number {}", number);
            valid = false;
        }
        if (!matcher.matches()) {
            log.error(stringNumber, "{} is not a valid integer");
            valid = false;
        }
        return valid;
    }

    public static boolean validNumber(double number) {
        boolean valid = true;
        Pattern pattern = Pattern.compile(regex);
        String stringNumber = String.valueOf(number);
        Matcher matcher = pattern.matcher(stringNumber);
        if (number < 0) {
            log.error("Cannot accept negative number {}", number);
            valid=false;
        }
        if (!matcher.matches()) {
            log.error(stringNumber, "{} is not a valid integer");
            valid=false;
        }
        return valid;
    }
}
