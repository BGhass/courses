package com.example.restback.service;

import com.example.restback.exception.AssesmentException;
import com.example.restback.model.Course;
import com.example.restback.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course add(Course course) {
        return this.courseRepository.save(course);
    }

    @Override
    public Course update(UUID id, Course course) {
        Course toUpdate = this.courseRepository.findById(id).orElseThrow(() -> new AssesmentException("Course not found"));

        toUpdate.setName(course.getName());
        toUpdate.setDescription(course.getDescription());

        return this.courseRepository.save(toUpdate);
    }

    @Override
    public void delete(UUID id) {
        Course toDelete = this.courseRepository.findById(id).orElseThrow(() -> new AssesmentException("Course not found"));

        this.courseRepository.delete(toDelete);
    }

    @Override
    public List<Course> get() {
        return this.courseRepository.findAll();
    }
}
