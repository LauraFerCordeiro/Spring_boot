package br.edu.ifsp.dsw3.trabalho.empresa.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Departament;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;
import jakarta.transaction.Transactional;

public interface DepartamentDAO extends JpaRepository<Departament, Long>{
   
}
