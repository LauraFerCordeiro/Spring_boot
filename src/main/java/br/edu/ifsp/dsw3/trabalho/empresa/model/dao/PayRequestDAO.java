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
    @Query("DELETE FROM PayRequest p WHERE p.company.id = ?1")
    public void deletePaysByCompanyId(Long id);

    @Query("SELECT p FROM PayRequest p WHERE p.company.id = ?1")
    public List<PayRequest> findPaysByCompanyId(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM PayRequest p WHERE p.request.id = ?1")
    public void deletePaysByRequestId(Long id);

    @Query("SELECT p FROM PayRequest p WHERE p.request.id = ?1")
    public List<PayRequest> findPaysByRequestId(Long id);
}
