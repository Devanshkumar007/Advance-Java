package com.assessment.service;

import com.assessment.dto.CourseRequest;
import com.assessment.dto.CourseResponse;
import com.assessment.dto.PagedResponse;

public interface CourseService {
    CourseResponse createCourse(Long instructorId, CourseRequest request);
    CourseResponse updateCourse(Long courseId, CourseRequest request);
    CourseResponse getCourseById(Long courseId);
    PagedResponse<CourseResponse> getCourses(int page, int size, String sortBy, String direction);
    void deleteCourse(Long courseId);
}
