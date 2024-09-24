package br.edu.ifsp.dsw3.trabalho.empresa.api_controller;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.CourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseRestController {

    @Autowired
    private CourseDAO courseDAO;

    // Buscar todos os cursos
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseDAO.findAll();
        return ResponseEntity.ok(courses);
    }

    // Buscar curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseDAO.findById(id);
        return course.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Criar um novo curso
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = courseDAO.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    // Atualizar um curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        Optional<Course> optionalCourse = courseDAO.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            // Atualizar os campos necessários
            course.setName(updatedCourse.getName());
            course.setStartDate(updatedCourse.getStartDate());
            course.setEndDate(updatedCourse.getEndDate());
            course.setCategory(updatedCourse.getCategory());
            course.setDescription(updatedCourse.getDescription());
            course.setCost(updatedCourse.getCost());
            course.setPays(updatedCourse.getPays());

            Course savedCourse = courseDAO.save(course);
            return ResponseEntity.ok(savedCourse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar um curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        Optional<Course> course = courseDAO.findById(id);
        if (course.isPresent()) {
            courseDAO.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
