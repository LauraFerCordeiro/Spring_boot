package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Course;
import jakarta.transaction.Transactional;

public interface CourseDAO extends JpaRepository<Course, Long> {
    
    @Transactional
    @Modifying
    @Query("UPDATE courses c SET c.name = ?2, c.value = ?3, c.description = ?4, c.end_date = ?5 WHERE c.id = ?1")
    public void updateCourse(Long id, String name, BigDecimal value, String description, LocalDate endDate);

}
