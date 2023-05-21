package com.example.restback.service;

import com.example.restback.model.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    Course add(Course course);
    Course update(UUID id, Course course);
    void delete(UUID id);
    List<Course> get();
}
