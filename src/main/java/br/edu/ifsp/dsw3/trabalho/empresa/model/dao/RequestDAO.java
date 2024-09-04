package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Request;
import jakarta.transaction.Transactional;

public interface RequestDAO extends JpaRepository<Request, Long>{
    @Transactional
    @Modifying
    @Query("DELETE FROM requests r WHERE r.id_company = ?1")
    public void deleteRequestsByCompanyId(Long id);

    @Query("SELECT r.id, r.name, r.description, r.startDate, r.endDate, r.priority, r.status, r.costEstimate, r.company_id FROM requests r WHERE r.company_id = ?1")
    public List<Request> findRequestsByCompanyId(Long id);  

}
