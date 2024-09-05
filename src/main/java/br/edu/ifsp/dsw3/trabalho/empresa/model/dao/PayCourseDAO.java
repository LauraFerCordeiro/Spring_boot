package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.PayCourse;
import jakarta.transaction.Transactional;

public interface PayCourseDAO extends JpaRepository<PayCourse, Long>{
    @Query("SELECT p  FROM PayCourse p WHERE p.course.id = ?1")
    public  List<PayCourse> findPaysByCourseId(Long id);

    @Query("SELECT p  FROM PayCourse p WHERE p.person.id = ?1")
    public List<PayCourse> findPaysByPersonId(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM PayCourse p WHERE p.course.id = ?1")
    public void deletePaysByCourseId(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM PayCourse p WHERE p.person.id = ?1")
    public void deletePaysByPersonId(Long id);
}
