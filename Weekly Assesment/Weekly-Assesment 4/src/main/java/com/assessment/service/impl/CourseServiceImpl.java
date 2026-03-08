package com.assessment.service.impl;

import com.assessment.dto.CourseRequest;
import com.assessment.dto.CourseResponse;
import com.assessment.dto.PagedResponse;
import com.assessment.entity.Course;
import com.assessment.entity.Role;
import com.assessment.entity.User;
import com.assessment.exception.InvalidRequestException;
import com.assessment.exception.ResourceNotFoundException;
import com.assessment.repository.CourseRepository;
import com.assessment.repository.UserRepository;
import com.assessment.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    @CacheEvict(value = {"courses", "courseById"}, allEntries = true)
    public CourseResponse createCourse(Long instructorId, CourseRequest request) {
        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id " + instructorId));

        if (instructor.getRole() != Role.INSTRUCTOR && instructor.getRole() != Role.ADMIN) {
            throw new InvalidRequestException("Only INSTRUCTOR or ADMIN can create courses");
        }

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setDuration(request.getDuration());
        course.setLevel(request.getLevel());
        course.setInstructor(instructor);

        return toResponse(courseRepository.save(course));
    }

    @Override
    @CacheEvict(value = {"courses", "courseById"}, allEntries = true)
    public CourseResponse updateCourse(Long courseId, CourseRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setDuration(request.getDuration());
        course.setLevel(request.getLevel());

        return toResponse(courseRepository.save(course));
    }

    @Override
    @Cacheable(value = "courseById", key = "#courseId")
    public CourseResponse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
        return toResponse(course);
    }

    @Override
    @Cacheable(value = "courses", key = "#page + '-' + #size + '-' + #sortBy + '-' + #direction")
    public PagedResponse<CourseResponse> getCourses(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Course> result = courseRepository.findAll(pageable);

        List<CourseResponse> items = result.getContent().stream().map(this::toResponse).toList();
        return new PagedResponse<>(items, result.getNumber(), result.getSize(), result.getTotalElements(), result.getTotalPages(), result.isLast());
    }

    @Override
    @CacheEvict(value = {"courses", "courseById"}, allEntries = true)
    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found with id " + courseId);
        }
        courseRepository.deleteById(courseId);
    }

    private CourseResponse toResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getDuration(),
                course.getLevel(),
                course.getInstructor() != null ? course.getInstructor().getId() : null,
                course.getInstructor() != null ? course.getInstructor().getFullName() : null,
                course.getCreatedAt()
        );
    }
}
