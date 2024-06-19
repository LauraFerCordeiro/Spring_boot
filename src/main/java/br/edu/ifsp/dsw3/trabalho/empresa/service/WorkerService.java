package br.edu.ifsp.dsw3.trabalho.empresa.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.ConsultancyDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.DepartamentDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.WorkerDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Consultancy;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Departament;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Worker;

@Service
public class WorkerService {
    @Autowired
    private WorkerDAO dao;

    @Autowired
    private DepartamentDAO daoD;

    @Autowired
    private ConsultancyDAO daoC;

    public Worker registerWorker(
            String name,
            String email,
            LocalDate birthDate,
            BigDecimal salary,
            String role,
            Long departamentId,
            Long consultancyId) {
        if (daoD.existsById(departamentId)) {
            Departament d = daoD.findById(departamentId).get();
            if (daoC.existsById(consultancyId)) {
                Consultancy c = daoC.findById(consultancyId).get();
                return dao.save(new Worker(name, email, birthDate, salary, role, d, c));
            }
            return dao.save(new Worker(name, email, birthDate, salary, role, d, null));
        } else {
            return null;
        }
    }

    public List<Worker> list() {
        return dao.findAll();
    }

    public Worker searchCod(Long id) {
        return dao.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean edit(Long id, String name, String email, LocalDate birthDate, BigDecimal salary, String role,
            Long departamentId, Long consultancyId) {
        if (dao.existsById(id) && daoD.existsById(departamentId)) {
            Departament d = daoD.findById(departamentId).get();
            if (daoC.existsById(consultancyId)) {
                Consultancy c = daoC.findById(consultancyId).get();
                dao.updateWorker(id, name, email, birthDate, salary, role, d, c);
            } else {
                dao.updateWorker(id, name, email, birthDate, salary, role, d, null);
            }
            return true;
        } else {
            return false;
        }
    }

    public List<Worker> searchName(String name) {
        return dao.findByName(name);
    }

    public List<Worker> searchSalary() {
        return dao.findSalary();
    }

    public List<Worker> searchRole(String role) {
        return dao.findRole(role);
    }
}