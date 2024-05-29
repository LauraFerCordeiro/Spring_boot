package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Departament;
import jakarta.transaction.Transactional;

public interface DepartamentDAO extends JpaRepository<Departament, Long>{
   
    @Transactional
    @Modifying
    @Query("UPDATE Departament d SET d.address = ?2, d.name = ?3, d.description = ?4 WHERE d.id = ?1")
    public void updateDepartament(Long id, String address, String name, String description);
}
