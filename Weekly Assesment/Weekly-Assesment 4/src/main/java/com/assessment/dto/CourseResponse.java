package com.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private String level;
    private Long instructorId;
    private String instructorName;
    private LocalDateTime createdAt;
}
