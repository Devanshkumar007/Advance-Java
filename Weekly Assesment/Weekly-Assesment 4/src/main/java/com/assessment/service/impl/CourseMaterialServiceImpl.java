package com.assessment.service.impl;

import com.assessment.dto.CourseMaterialResponse;
import com.assessment.entity.Course;
import com.assessment.entity.CourseMaterial;
import com.assessment.exception.FileStorageException;
import com.assessment.exception.ResourceNotFoundException;
import com.assessment.repository.CourseMaterialRepository;
import com.assessment.repository.CourseRepository;
import com.assessment.service.CourseMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseMaterialServiceImpl implements CourseMaterialService {

    private final CourseRepository courseRepository;
    private final CourseMaterialRepository courseMaterialRepository;

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    @Override
    public CourseMaterialResponse uploadMaterial(Long courseId, String title, MultipartFile file) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));

        if (file == null || file.isEmpty()) {
            throw new FileStorageException("File is required");
        }

        String cleanName = StringUtils.cleanPath(file.getOriginalFilename() == null ? "file" : file.getOriginalFilename());
        String storedFileName = UUID.randomUUID() + "_" + cleanName;

        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(uploadPath);
            Files.copy(file.getInputStream(), uploadPath.resolve(storedFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Could not store file", e);
        }

        CourseMaterial material = new CourseMaterial();
        material.setTitle(title);
        material.setFileName(storedFileName);
        material.setFileType(file.getContentType());
        material.setFileUrl("/api/materials/download/" + storedFileName);
        material.setUploadDate(LocalDateTime.now());
        material.setCourse(course);

        return toResponse(courseMaterialRepository.save(material));
    }

    @Override
    public List<CourseMaterialResponse> getByCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found with id " + courseId);
        }

        return courseMaterialRepository.findByCourseId(courseId).stream().map(this::toResponse).toList();
    }

    @Override
    public Resource loadAsResource(Long materialId) {
        CourseMaterial material = courseMaterialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found with id " + materialId));

        Path filePath = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(material.getFileName());
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            throw new FileStorageException("Could not read file", e);
        }

        throw new FileStorageException("File not found for material id " + materialId);
    }

    @Override
    public String getDownloadFileName(Long materialId) {
        CourseMaterial material = courseMaterialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found with id " + materialId));
        return material.getFileName();
    }

    private CourseMaterialResponse toResponse(CourseMaterial material) {
        return new CourseMaterialResponse(
                material.getId(),
                material.getTitle(),
                material.getFileName(),
                material.getFileType(),
                material.getFileUrl(),
                material.getUploadDate(),
                material.getCourse().getId()
        );
    }
}
