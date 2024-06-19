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

public interface ConsultancyDAO extends JpaRepository<Consultancy, Long>{
    
    @Transactional
    @Modifying
    @Query("UPDATE consultancies c SET c.value = ?2, c.end_date = ?3, c.description = ?4, c.client = ?5, c.worker = ?6 WHERE c.id = ?1")
    public void updateConsultancy(Long id, BigDecimal value, LocalDate endDate, String description, Client client, Worker worker);
}
