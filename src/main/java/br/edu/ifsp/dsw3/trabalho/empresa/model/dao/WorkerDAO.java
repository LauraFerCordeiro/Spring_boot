package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;
import jakarta.transaction.Transactional;

public interface WorkerDAO extends JpaRepository<Worker, Long> {
    @Query("SELECT w FROM Worker w WHERE w.departament.id = ?1")
    public List<Worker> buscarWorkers (Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Worker w WHERE w.departament.id = ?1")
    public void excluirWorkers (Long id);
}
