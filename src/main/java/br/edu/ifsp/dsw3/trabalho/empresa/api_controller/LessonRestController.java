package br.edu.ifsp.dsw3.trabalho.empresa.api_controller;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.LessonDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lessons")
public class LessonRestController {

    @Autowired
    private LessonDAO lessonDAO;

    // Buscar todas as aulas
    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonDAO.findAll();
        return ResponseEntity.ok(lessons);
    }

    // Buscar aula por ID
    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        Optional<Lesson> lesson = lessonDAO.findById(id);
        return lesson.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Criar uma nova aula
    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        Lesson savedLesson = lessonDAO.save(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLesson);
    }

    // Atualizar uma aula existente
    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson updatedLesson) {
        Optional<Lesson> optionalLesson = lessonDAO.findById(id);
        if (optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            // Atualizar os campos necessários
            lesson.setName(updatedLesson.getName());
            lesson.setRelease(updatedLesson.getRelease());
            lesson.setLessonLink(updatedLesson.getLessonLink());
            lesson.setCourse(updatedLesson.getCourse());

            Lesson savedLesson = lessonDAO.save(lesson);
            return ResponseEntity.ok(savedLesson);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar uma aula
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        Optional<Lesson> lesson = lessonDAO.findById(id);
        if (lesson.isPresent()) {
            lessonDAO.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

