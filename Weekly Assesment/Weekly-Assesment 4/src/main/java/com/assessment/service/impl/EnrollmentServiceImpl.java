package com.assessment.service.impl;

import com.assessment.dto.EnrollmentResponse;
import com.assessment.dto.EnrollmentUpdateRequest;
import com.assessment.entity.Course;
import com.assessment.entity.Enrollment;
import com.assessment.entity.Role;
import com.assessment.entity.User;
import com.assessment.exception.InvalidRequestException;
import com.assessment.exception.ResourceNotFoundException;
import com.assessment.repository.CourseRepository;
import com.assessment.repository.EnrollmentRepository;
import com.assessment.repository.UserRepository;
import com.assessment.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public EnrollmentResponse enrollStudent(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));

        if (student.getRole() != Role.STUDENT) {
            throw new InvalidRequestException("Only users with STUDENT role can enroll");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));

        enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .ifPresent(e -> {
                    throw new InvalidRequestException("Student is already enrolled in this course");
                });

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return toResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    public EnrollmentResponse updateEnrollment(Long enrollmentId, EnrollmentUpdateRequest request) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id " + enrollmentId));

        if (request.getStatus() == null && request.getProgressPercentage() == null) {
            throw new InvalidRequestException("At least one field is required: status or progressPercentage");
        }

        if (request.getStatus() != null) {
            enrollment.setStatus(request.getStatus());
        }
        if (request.getProgressPercentage() != null) {
            enrollment.setProgressPercentage(request.getProgressPercentage());
        }

        return toResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    public List<EnrollmentResponse> getEnrollmentsByStudent(Long studentId) {
        if (!userRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with id " + studentId);
        }

        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(this::toResponse)
                .toList();
    }

    private EnrollmentResponse toResponse(Enrollment enrollment) {
        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getStudent().getId(),
                enrollment.getStudent().getFullName(),
                enrollment.getCourse().getId(),
                enrollment.getCourse().getTitle(),
                enrollment.getStatus().name(),
                enrollment.getProgressPercentage(),
                enrollment.getEnrollmentDate()
        );
    }
}
