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
    @Query("DELETE FROM Request r WHERE r.company.id = ?1")
    public void deleteRequestsByCompanyId(Long id);

    @Query("SELECT r FROM Request r WHERE r.company.id = ?1")
    public List<Request> findRequestsByCompanyId(Long id);  

}
