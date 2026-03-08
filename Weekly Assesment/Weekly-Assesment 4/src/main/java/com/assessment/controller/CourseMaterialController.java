package com.assessment.controller;

import com.assessment.dto.CourseMaterialResponse;
import com.assessment.service.CourseMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class CourseMaterialController {

    private final CourseMaterialService courseMaterialService;

    @Operation(summary = "Upload course material")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CourseMaterialResponse> upload(
            @RequestParam Long courseId,
            @RequestParam String title,
            @RequestParam MultipartFile file
    ) {
        return ResponseEntity.ok(courseMaterialService.uploadMaterial(courseId, title, file));
    }

    @Operation(summary = "Get all materials by course")
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseMaterialResponse>> byCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseMaterialService.getByCourse(courseId));
    }

    @Operation(summary = "Download material file by material ID")
    @GetMapping("/download/{materialId}")
    public ResponseEntity<Resource> download(@PathVariable Long materialId) {
        Resource resource = courseMaterialService.loadAsResource(materialId);
        String fileName = courseMaterialService.getDownloadFileName(materialId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
