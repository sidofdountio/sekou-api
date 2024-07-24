package com.sidof.dto;

import com.sidof.model.CourseOffering;
import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Year;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 16/07/2024  <br>
 * Version    : v1.0.0
 */
@Data
@AllArgsConstructor
@Builder
public class CourseOfferingDto {
    private CharSequence startTime;
    private CharSequence endTime;
    private DayOfWeek dayOfWeek;
    private Long levelId;
    private Long optionId;
    private Long courseId;
    private Long teacherId;
    private Year year;



    public static CourseOfferingDto getCourseOfferingDto(CourseOfferingDto dto) {
        return null;
    }


}
