package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Client;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Consultancy;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;
import jakarta.transaction.Transactional;

public interface ConsultancyDAO extends JpaRepository<Consultancy, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Consultancy c SET c.cost = ?2, c.endDate = ?3, c.description = ?4, c.client = ?5, c.worker = ?6 WHERE c.id = ?1")
    public void updateConsultancy(Long id, BigDecimal cost, LocalDate endDate, String description, Client client, Worker worker);

    @Transactional
    @Modifying
    @Query("UPDATE Consultancy c SET c.cost = ?2, c.endDate = ?3, c.description = ?4, c.client = ?5 WHERE c.id = ?1")
    public void updateConsultancy(Long id, BigDecimal cost, LocalDate endDate, String description, Client client);

    @Transactional
    @Modifying
    @Query("UPDATE Consultancy c SET c.cost = ?2, c.endDate = ?3, c.description = ?4, c.worker = ?5 WHERE c.id = ?1")
    public void updateConsultancy(Long id, BigDecimal cost, LocalDate endDate, String description, Worker worker);

    @Transactional
    @Modifying
    @Query("UPDATE Consultancy c SET c.cost = ?2, c.endDate = ?3, c.description = ?4 WHERE c.id = ?1")
    public void updateConsultancy(Long id, BigDecimal cost, LocalDate endDate, String description);

}
