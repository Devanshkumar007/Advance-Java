package com.assessment.controller;

import com.assessment.dto.EnrollmentResponse;
import com.assessment.dto.EnrollmentUpdateRequest;
import com.assessment.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Operation(summary = "Enroll student into a course")
    @PostMapping
    public ResponseEntity<EnrollmentResponse> enroll(@RequestParam Long studentId, @RequestParam Long courseId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enrollStudent(studentId, courseId));
    }

    @Operation(summary = "Update enrollment status/progress")
    @PatchMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentResponse> update(@PathVariable Long enrollmentId, @Valid @RequestBody EnrollmentUpdateRequest request) {
        return ResponseEntity.ok(enrollmentService.updateEnrollment(enrollmentId, request));
    }

    @Operation(summary = "Get enrollments by student")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponse>> byStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
    }
}
