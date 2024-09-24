package br.edu.ifsp.dsw3.trabalho.empresa.api_controller;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.PayCourseDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.PayCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pay-courses")
public class PayCourseRestController {

    @Autowired
    private PayCourseDAO payCourseDAO;

    // Buscar todos os pagamentos de cursos
    @GetMapping
    public ResponseEntity<List<PayCourse>> getAllPayCourses() {
        List<PayCourse> payCourses = payCourseDAO.findAll();
        return ResponseEntity.ok(payCourses);
    }

    // Buscar pagamento de curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<PayCourse> getPayCourseById(@PathVariable Long id) {
        Optional<PayCourse> payCourse = payCourseDAO.findById(id);
        return payCourse.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Criar um novo pagamento de curso
    @PostMapping
    public ResponseEntity<PayCourse> createPayCourse(@RequestBody PayCourse payCourse) {
        PayCourse savedPayCourse = payCourseDAO.save(payCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayCourse);
    }

    // Atualizar um pagamento de curso existente
    @PutMapping("/{id}")
    public ResponseEntity<PayCourse> updatePayCourse(@PathVariable Long id, @RequestBody PayCourse updatedPayCourse) {
        Optional<PayCourse> optionalPayCourse = payCourseDAO.findById(id);
        if (optionalPayCourse.isPresent()) {
            PayCourse payCourse = optionalPayCourse.get();
            // Atualizar os campos necessários
            payCourse.setDate(updatedPayCourse.getDate());
            payCourse.setCost(updatedPayCourse.getCost());
            payCourse.setCourse(updatedPayCourse.getCourse());
            payCourse.setPerson(updatedPayCourse.getPerson());

            PayCourse savedPayCourse = payCourseDAO.save(payCourse);
            return ResponseEntity.ok(savedPayCourse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar um pagamento de curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayCourse(@PathVariable Long id) {
        Optional<PayCourse> payCourse = payCourseDAO.findById(id);
        if (payCourse.isPresent()) {
            payCourseDAO.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

