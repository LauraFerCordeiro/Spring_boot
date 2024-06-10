package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Departament;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;
import jakarta.transaction.Transactional;

public interface WorkerDAO extends JpaRepository<Worker, Long> {
    @Query("SELECT w FROM Worker w WHERE w.departament.id = ?1")
    public List<Worker> findWorkers (Long id);

    @Query("SELECT w.id, w.name, w.email, w.birthDate, w.salary, w.role FROM Worker w WHERE w.departament.id = ?1")
    public List<Object[]> findWorkersDepartament(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Worker w WHERE w.departament.id = ?1")
    public void deleteWorkers (Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Worker w SET w.name = ?2, w.email = ?3, w.birthDate = ?4, w.salary = ?5, w.role = ?6, w.departament = ?7 WHERE w.id = ?1")
    public void updateWorker (Long id, String name, String email, LocalDate birthDate, BigDecimal salary, String role, Departament departament);

    @Query("SELECT w FROM Worker w WHERE w.name LIKE %?1%")
    public List<Worker> findByName (String name);

    @Query("SELECT w FROM Worker w ORDER BY w.salary DESC")
    public List<Worker> findSalary ();

    @Query("SELECT w FROM Worker w WHERE w.role LIKE %?1% ORDER BY w.birthDate ASC")
    public List<Worker> findRole(String role);

}
