package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

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
    @Query("UPDATE clients c SET c.name = ?2, c.email = ?3, c.consultancy = ?4, c.course = ?5 WHERE c.id = ?1")
    public void updateClient(Long id, String name, String email, Consultancy consultancy, Course course);

}
