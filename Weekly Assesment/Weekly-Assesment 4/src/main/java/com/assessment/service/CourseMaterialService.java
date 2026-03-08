package com.assessment.service;

import com.assessment.dto.CourseMaterialResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseMaterialService {
    CourseMaterialResponse uploadMaterial(Long courseId, String title, MultipartFile file);
    List<CourseMaterialResponse> getByCourse(Long courseId);
    Resource loadAsResource(Long materialId);
    String getDownloadFileName(Long materialId);
}
