package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Client;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Consultancy;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Course;
import jakarta.transaction.Transactional;

public interface ClientDAO extends JpaRepository<Client, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.name = ?2, c.email = ?3, c.consultancy = ?4, c.course = ?5 WHERE c.id = ?1")
    public void updateClient(Long id, String name, String email, Consultancy consultancy, Course course);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.name = ?2, c.email = ?3, c.consultancy = ?4 WHERE c.id = ?1")
    public void updateClient(Long id, String name, String email, Consultancy consultancy);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.name = ?2, c.email = ?3, c.course = ?4 WHERE c.id = ?1")
    public void updateClient(Long id, String name, String email, Course course);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.name = ?2, c.email = ?3 WHERE c.id = ?1")
    public void updateClient(Long id, String name, String email);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.course = NULL WHERE c.course.id = ?1")
    public void removeCourseId(Long id);

    @Query("SELECT c.id, c.name, c.email FROM Client c WHERE c.course.id = ?1")
    public List<Object[]> findClientsByCourse(Long id);
}
