package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.PayRequest;
import jakarta.transaction.Transactional;

public interface PayRequestDAO extends JpaRepository<PayRequest, Long>{
    @Transactional
    @Modifying
    @Query("DELETE FROM pay_request p WHERE p.id_company = ?1")
    public void deletePaysCompany(Long id);

    @Query("SELECT p.id, p.date, p.cost, p.company_id, p.request_id FROM pay_request p WHERE p.company_id = ?1")
    public List<PayRequest> findPaysByCompanyId(Long id);  

}
