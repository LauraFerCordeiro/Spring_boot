package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;
import jakarta.transaction.Transactional;

public interface WorkerDAO extends JpaRepository<Worker, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Worker w WHERE w.department.id = ?1")
    public void deleteWorkersByDepartmentId(Long id);

    @Query("SELECT w FROM Worker w WHERE w.department.id = ?1")
    public List<Worker> findWorkersByDepartmentId(Long id);
}
