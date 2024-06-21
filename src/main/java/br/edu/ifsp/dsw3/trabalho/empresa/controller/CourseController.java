package br.edu.ifsp.dsw3.trabalho.empresa.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Course;
import br.edu.ifsp.dsw3.trabalho.empresa.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/register")
    public ResponseEntity<Course> registerCourse(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "cost") BigDecimal cost,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "endDate") LocalDate endDate) {
        Course course = courseService.register(name, cost, description, endDate);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Course>> list() {
        List<Course> courses = courseService.list();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Course> searchCod(@PathVariable("id") Long id) {
        Course course = courseService.searchCod(id);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        boolean deleted = courseService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Boolean> edit(
            @PathVariable("id") Long id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "cost") BigDecimal cost,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "endDate") LocalDate endDate) {
        boolean edited = courseService.edit(id, name, cost, description, endDate);
        if (edited) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listClients/{id}")
    public ResponseEntity<List<Object[]>> listClients(@PathVariable("id") Long id) {
        List<Object[]> clients = courseService.listClients(id);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
