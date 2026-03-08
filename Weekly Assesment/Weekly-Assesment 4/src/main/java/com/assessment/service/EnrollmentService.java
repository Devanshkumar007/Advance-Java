package com.assessment.service;

import com.assessment.dto.EnrollmentResponse;
import com.assessment.dto.EnrollmentUpdateRequest;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse enrollStudent(Long studentId, Long courseId);
    EnrollmentResponse updateEnrollment(Long enrollmentId, EnrollmentUpdateRequest request);
    List<EnrollmentResponse> getEnrollmentsByStudent(Long studentId);
}
