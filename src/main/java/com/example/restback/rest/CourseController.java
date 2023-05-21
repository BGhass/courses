package com.example.restback.rest;

import com.example.restback.model.Course;
import com.example.restback.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public List<Course> getAll() {
        return this.courseService.get();
    }

    @PostMapping()
    public Course add(@RequestBody Course course) {
        return this.courseService.add(course);
    }

    @PutMapping()
    public Course update(@PathVariable("id") UUID id, Course course) {
        return this.courseService.update(id, course);
    }

    @DeleteMapping()
    public void delete(@PathVariable("id") UUID id) {
        this.courseService.delete(id);
    }
}
